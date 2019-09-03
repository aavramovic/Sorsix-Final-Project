import {Component, OnInit} from '@angular/core';
import {FormControl, FormGroup} from '@angular/forms';
import {of, Subject} from 'rxjs';
import {debounceTime, distinctUntilChanged, flatMap, switchMap} from 'rxjs/operators';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {ChangeRole} from '../models/change-role';
import {CHANGE_ROLE_URL, SEARCH_URL} from '../../../../Models/global-const-url-paths';
import {ISearchedUser} from '../models/isearched-user';
import {MatRadioChange} from '@angular/material';

@Component({
    selector: 'app-search-user',
    templateUrl: './search-user.component.html',
    styleUrls: ['./search-user.component.css']
})
export class SearchUserComponent implements OnInit {
    roles: string[] = [
        'USER',
        'MODERATOR',
        'ADMIN'
    ];

    searchForm = new FormGroup({
        searchTerm: new FormControl('')
    });
    userSearchResult: Array<ISearchedUser> = [];

    search$ = new Subject();

    constructor(private httpClient: HttpClient) {
    }

    ngOnInit(): void {
        this.search$.pipe(
            flatMap(
                () => this.searchTerm.valueChanges.pipe(
                    debounceTime(300),
                    distinctUntilChanged(),
                    switchMap((term: string) => this.userSearchService(term))
                )
            )
        ).subscribe(
            result => {
                this.userSearchResult = result;
            }
        );
        this.search$.next();
    }

    get searchTerm(): FormControl {
        return (this.searchForm.get('searchTerm') as FormControl);
    }

    onSubmit() {
        this.search$.next();
    }

    userSearchService(searchTerm) {
        if (!searchTerm.trim()) {
            return of([]);
        }
        return this.httpClient.get<ISearchedUser[]>(
            `${SEARCH_URL}${searchTerm}`,
            {
                headers: new HttpHeaders({
                    'Content-Type': 'application/json',
                    'Authorization': 'Bearer ' + localStorage.getItem('id_token')
                })
            });

    }

    radioChangedHandler(event: MatRadioChange, username: string) {

        this.httpClient.post(
            CHANGE_ROLE_URL,
            new ChangeRole(username, event.value),
            {
                headers: new HttpHeaders(
                    {
                        'Content-Type': 'application/json',
                        'Authorization': 'Bearer ' + localStorage.getItem('id_token')
                    })
            }).subscribe(
        );
    }

    loggedInUsername() {
        return localStorage.getItem('username');
    }

}
