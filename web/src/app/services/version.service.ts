import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import {Observable} from 'rxjs/Observable';

import { environment } from '../../environments/environment';

@Injectable()
export class VersionService {

  constructor(private http: HttpClient) { }

  public getVersion(): Observable<string> {
    return this.http.get(environment.services_basepath + '/version', {responseType: 'text'});
    // return "123";

  }

}
