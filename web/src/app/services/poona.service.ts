import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs/Observable';
import 'rxjs/add/operator/map';

import {environment} from '../../environments/environment';
import {Instance} from '../models/instance.model';

@Injectable()
export class PoonaService {
  constructor(private http: HttpClient) {}

  public getFederation(): Observable<Instance> {
    return this.http
      .get(environment.services_basepath + '/poona/federation')
      .map((res: any) => this.mapInstance(res));
  }

  public getLigues(): Observable<Instance[]> {
    return this.http
      .get(environment.services_basepath + '/poona/federation/ligue')
      .map((res: any) => this.mapListeInstances(res));
  }

  public getCodeps(ligueId: number): Observable<Instance[]> {
    return this.http
      .get(
      environment.services_basepath +
      '/poona/federation/ligue/' +
      ligueId +
      '/codep'
      )
      .map((res: any) => this.mapListeInstances(res));
  }

  public getClubs(ligueId: number, codepId: number): Observable<Instance[]> {
    return this.http
      .get(
      environment.services_basepath +
      '/poona/federation/ligue/' +
      ligueId +
      '/codep/' +
      codepId +
      '/club'
      )
      .map((res: any) => this.mapListeInstances(res));
  }

  public getClub(clubId: number): Observable<Instance> {
    return this.http
      .get(environment.services_basepath + '/poona/federation/club/' + clubId)
      .map((res: any) => this.mapInstance(res));
  }

  private mapInstance(res: any): Instance {
    const instance: Instance = {
      id: res.INS_ID,
      nom: res.INS_NOM,
      urlLogo: res.INS_LOGO
    };
    return instance;
  }

  private mapListeInstances(res: any[]): Instance[] {
    const instances: Instance[] = [];
    for (const r of res) {
      instances.push(this.mapInstance(r));
    }
    return instances;
  }
}
