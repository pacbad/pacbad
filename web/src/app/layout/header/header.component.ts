import { Component, OnInit } from '@angular/core';
import {Observable} from 'rxjs/Observable';

import { AuthentificationService } from '../../services/authentification.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html'
})
export class HeaderComponent implements OnInit {

  private connecte: boolean;
  private username: string;

  constructor(private authentificationService: AuthentificationService) { }
  
  ngOnInit() {
    this.username = this.authentificationService.getUserInfo();
    this.connecte = this.username != undefined;
  }

}
