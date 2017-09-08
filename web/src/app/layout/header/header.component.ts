import { Component, OnInit } from '@angular/core';
import {Observable} from 'rxjs/Observable';

import { AuthentificationService } from '../../services/authentification.service';

import {Claims} from '../../models/user.model';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html'
})
export class HeaderComponent implements OnInit {

  private connecte: boolean;
  private userInfo: Claims;

  constructor(private authentificationService: AuthentificationService) { }
  
  ngOnInit() {
    let userInfo: any = this.authentificationService.getUserInfo();
    this.userInfo = userInfo;
    this.connecte = false;
    if (this.userInfo) {
      this.connecte = this.userInfo.identifiant != undefined;
    }
  }
  
  logout() {
    this.authentificationService.logout();
    window.location.href = '/';
  }

}
