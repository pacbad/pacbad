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
  
  private currentRouteSnapshot: ActivatedRouteSnapshot;

  constructor(private router: Router, private currentRouteOnInit: ActivatedRoute, private title: Title, private authentificationService: AuthentificationService) { }
  
  ngOnInit() {
    let userInfo: any = this.authentificationService.getUserInfo();
    this.userInfo = userInfo;
    this.connecte = false;
    if (this.userInfo) {
      this.connecte = this.userInfo.identifiant != undefined;
    }
    
    this.setTitleForRoute(this.currentRouteOnInit);
    
    this.router.events.subscribe(event => {
      if (event instanceof ResolveEnd) {
        this.currentRouteSnapshot = event.state.root;
        this.setTitleForRoute(this.currentRouteSnapshot);
      }
    });
  }
  
  logout() {
    this.authentificationService.logout();
    if (this.routeNeedLogin(this.currentRouteSnapshot)) {
      window.location.href = '/';
    } else {
      window.location.reload();
    }
  }
  
  private setTitleForRoute(route: any) {
    while (route && route.firstChild) {
      route = route.firstChild;
    }
    let dataTitle: string = this.readDataTitleFromRoute(route);
    while (!dataTitle && route) {
      route = route.parent;
      if (route) {
        dataTitle = this.readDataTitleFromRoute(route);
      }
    }
    if (dataTitle) {
      this.title.setTitle(dataTitle + ' | PacBad');
    } else {
      this.title.setTitle('PacBad')
    }
  }
  
  private readDataTitleFromRoute(route: any) {
    if (route.data) {
      if (route.data.title) {
        return route.data.title;
      }
      if (route.data.value) {
        return route.data.value.title;
      }
    }
    return undefined;
  }
  
  private routeNeedLogin(route: ActivatedRouteSnapshot): boolean {
    while (route && route.firstChild) {
      route = route.firstChild;
    }
    let dataLogin: boolean = route.data.login ? route.data.login : false;
    while (!dataLogin && route) {
      route = route.parent;
      if (route) {
        dataLogin = route.data.login ? route.data.login : false;
      }
    }
    return dataLogin;
  }

}
