import { Component, OnInit } from '@angular/core';
import {Observable} from 'rxjs/Observable';
import { Title } from '@angular/platform-browser';
import { Router, ResolveEnd, ActivatedRoute, ActivatedRouteSnapshot } from '@angular/router';

import { AuthentificationService } from '../../services/authentification.service';

import {Claims} from '../../models/user.model';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html'
})
export class HeaderComponent implements OnInit {

  private connecte: boolean;
  private userInfo: Claims;
  
  private currentRoute: any;

  constructor(private router: Router, private currentRouteOnInit: ActivatedRoute, private title: Title, private authentificationService: AuthentificationService) {
    this.currentRoute = this.currentRouteOnInit;
  }
  
  ngOnInit() {
    let userInfo: any = this.authentificationService.getUserInfo();
    this.userInfo = userInfo;
    this.connecte = false;
    if (this.userInfo) {
      this.connecte = this.userInfo.identifiant != undefined;
    }
    
    this.setTitle();
    
    this.router.events.subscribe(event => {
      if (event instanceof ResolveEnd) {
        this.currentRoute = event.state.root;
        this.setTitle();
      }
    });
  }
  
  logout() {
    this.authentificationService.logout();
    if (this.routeNeedLogin()) {
      window.location.href = '/';
    } else {
      window.location.reload();
    }
  }
  
  private setTitle() {
    let route: any = this.currentRoute;
    while (route && route.firstChild) {
      route = route.firstChild;
    }
    let dataTitle: string = this.readDataFromRoute(route, 'title');
    while (!dataTitle && route) {
      route = route.parent;
      if (route) {
        dataTitle = this.readDataFromRoute(route, 'title');
      }
    }
    if (dataTitle) {
      this.title.setTitle(dataTitle + ' | PacBad');
    } else {
      this.title.setTitle('PacBad')
    }
  }
  
  private readDataFromRoute(route: any, key: string) {
    if (route.data) {
      if (route.data[key]) {
        return route.data[key];
      }
      if (route.data.value) {
        return route.data.value[key];
      }
    }
    return undefined;
  }
  
  private routeNeedLogin(): boolean {
    let route: any = this.currentRoute;
    while (route && route.firstChild) {
      route = route.firstChild;
    }
    
    let dataLogin: boolean = this.readDataFromRoute(route, 'login');
    while (!dataLogin && route) {
      route = route.parent;
      if (route) {
        dataLogin = this.readDataFromRoute(route, 'login');
      }
    }
    return dataLogin;
  }

}
