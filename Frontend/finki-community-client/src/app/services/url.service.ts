import { Injectable } from '@angular/core';
import {Location} from '@angular/common';

@Injectable({
  providedIn: 'root'
})
export class UrlService {

  constructor(private  location: Location) { }


    getLastPartOfUrl() {
        return location.normalize(location.path()).split('/').pop();
    }
}
