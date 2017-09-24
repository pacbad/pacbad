import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import {Observable} from 'rxjs/Observable';

import { environment } from '../../environments/environment';
import { Parametre } from '../models/parametre.model';
import { User } from '../models/user.model';

@Injectable()
export class AdminService {

  constructor(private http: HttpClient) { }

  public clearCache(): Observable<any> {
    return this.http.delete(environment.services_basepath + '/admin/cache');
  }

  public getAdministrateurs(): Observable<User[]> {
    return this.http.get(environment.services_basepath + '/admin/administrateur');
  }

  public deleteAdministrateur(user: User): Observable<any> {
    return this.http.delete(environment.services_basepath + '/admin/administrateur/' + user.identifiant);
  }

  public addAdministrateur(user: User): Observable<any> {
    return this.http.put(environment.services_basepath + '/admin/administrateur/' + user.identifiant, undefined);
  }

  public getParametres(): Observable<Parametre[]> {
    return this.http.get(environment.services_basepath + '/admin/parametre');
  }

  public updateParametre(parametre: Parametre): Observable<any> {
    return this.http.put(environment.services_basepath + '/admin/parametre', parametre);
  }
  
}
