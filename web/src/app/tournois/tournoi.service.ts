import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';

import { Tournoi } from '../models/tournoi.model';
import { environment } from '../../environments/environment';

@Injectable()
export class TournoiService {
  constructor(private http: HttpClient) {}

  public getTournois(): Observable<any> {
    return this.http.get(environment.services_basepath + '/tournoi');
  }

  public getTournoi(identifiant: number): Observable<Tournoi> {
    return this.http.get(
      environment.services_basepath + '/tournoi/' + identifiant
    );
  }
}
