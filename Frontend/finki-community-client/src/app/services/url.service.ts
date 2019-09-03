import {Injectable} from '@angular/core';
import {Location} from '@angular/common';
import {Subject} from 'rxjs';

@Injectable({
    providedIn: 'root'
})
export class UrlService {

    isHidden$: Subject<boolean> = new Subject<boolean>();

    constructor(private  location: Location) {
    }


    getLastPartOfUrl() {
        return this.location.normalize(this.location.path()).split('/').pop();
    }

    hasStartInUrl(): boolean {
        return this.location.normalize(this.location.path()).split('/').includes('start');
    }

    urlLengthInParts(): number {
        return this.location.normalize(this.location.path()).split('/').length;
    }

    getUrl(): string {
        return this.location.path();
    }

    urlEndsWith(args: string[]): boolean {
        args.forEach(arg => {
            if (this.location.normalize(this.location.path()).slice(1) === arg) {
                return true;
            }
        });
        return false;
    }
}
