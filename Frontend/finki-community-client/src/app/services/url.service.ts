import {Injectable} from '@angular/core';
import {Location} from '@angular/common';

@Injectable({
    providedIn: 'root'
})
export class UrlService {

    constructor(private  location: Location) {
    }


    getLastPartOfUrl() {
        return this.location.normalize(this.location.path()).split('/').pop();
    }

    containsStartInUrl() {
        return this.location.normalize(this.location.path()).split('/').includes('start');
    }
}
