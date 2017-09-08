import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import {Observable} from 'rxjs/Observable';
import 'rxjs/add/operator/do';
import { User } from '../models/user.model';

import { environment } from '../../environments/environment';

@Injectable()
export class AuthentificationService {

  constructor(private http: HttpClient) { }

  public login(user: User) {
    let loginObs: Observable<any> = this.http.post(environment.services_basepath + '/auth/login', user);
    
    // Stockage des informations de l'utilisateur connecté dans le localStorage
    return loginObs.do(userInfo => {
      userInfo.identifiant = userInfo.sub;
      localStorage.setItem('currentUser', JSON.stringify(userInfo));
    });
  }

  public register(user: User) {
    let registerObs: Observable<any> = this.http.post(environment.services_basepath + '/auth/register', user);
    
    // Stockage des informations de l'utilisateur connecté dans le localStorage
    return registerObs.do(userInfo => {
      userInfo.identifiant = userInfo.sub;
      localStorage.setItem('currentUser', JSON.stringify(userInfo));
    });
  }

  public update(user: User) {
    let updateObs: Observable<any> = this.http.put(environment.services_basepath + '/auth/user', user);
    
    // Stockage des informations de l'utilisateur connecté dans le localStorage
    return updateObs.do(userInfo => {
      userInfo.identifiant = userInfo.sub;
      localStorage.setItem('currentUser', JSON.stringify(userInfo));
    });
  }

  public getInfoFromServer(): Observable<User> {
    return this.http.get(environment.services_basepath + '/auth/user');
  }
  
  public logout() {
    this.clearSession();
  }
  
  public clearSession() {
    // remove user from local storage to log user out
    localStorage.removeItem('currentUser');
  }
  
  public validateToken() {
    let headers: HttpHeaders = this.getAuthorizationHeader();
    if (headers) {
      this.http.post(environment.services_basepath + '/auth/validate', {})
        .subscribe();
    }
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
