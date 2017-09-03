import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import {Observable} from 'rxjs/Observable';

import { environment } from '../../environments/environment';

@Injectable()
export class AuthentificationService {

  constructor(private http: HttpClient) { }

  public login(login: string, password: string) {
    let loginObs: Observable<any> = this.http.post(environment.services_basepath + '/login', JSON.stringify({ login: login, password: password }));
    
    // Stockage des informations de l'utilisateur connecté dans le localStorage
    loginObs.subscribe(resp => {
      let bearer: string = resp.headers.get('Authorization');
      console.log('bearer = ', bearer);
      let userInfo: any = resp.body;
      console.log('userInfo = ', userInfo);
      localStorage.setItem('currentUser', userInfo);
      localStorage.setItem('bearer', bearer);
    });
    
    return loginObs;
  }
  
  public logout() {
    // remove user from local storage to log user out
    localStorage.removeItem('currentUser');
    localStorage.removeItem('bearer');
  }
  
  public getUserInfo(): any {
    return localStorage.getItem('currentUser');
  }
  
  public getBearer(): string {
    return localStorage.getItem('bearer');
  }

}
