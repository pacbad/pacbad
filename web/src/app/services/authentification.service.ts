import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import {Observable} from 'rxjs/Observable';
import { User } from '../models/user.model';

import { environment } from '../../environments/environment';

@Injectable()
export class AuthentificationService {

  constructor(private http: HttpClient) { }

  public login(user: User) {
    let loginObs: Observable<any> = this.http.post(environment.services_basepath + '/auth/login', user);
    
    // Stockage des informations de l'utilisateur connecté dans le localStorage
    loginObs.subscribe(userInfo => {
      localStorage.setItem('currentUser', JSON.stringify(userInfo));
    });
    
    return loginObs;
  }

  public register(user: User) {
    let registerObs: Observable<any> = this.http.post(environment.services_basepath + '/auth/register', user);
    
    // Stockage des informations de l'utilisateur connecté dans le localStorage
    registerObs.subscribe(userInfo => {
      localStorage.setItem('currentUser', JSON.stringify(userInfo));
    });
    
    return registerObs;
  }
  
  public logout() {
    // remove user from local storage to log user out
    localStorage.removeItem('currentUser');
  }
  
  public getUserInfo(): any {
    return JSON.parse(localStorage.getItem('currentUser'));
  }

  public getAuthorizationHeader(): HttpHeaders {
    let currentUser = JSON.parse(localStorage.getItem('currentUser'));
    if (currentUser && currentUser.token) {
      return new HttpHeaders().set('Authorization', 'Bearer ' + currentUser.token);
    }
    return null;
  }

}
