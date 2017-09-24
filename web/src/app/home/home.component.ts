import { Component, OnInit } from '@angular/core';

import { AuthentificationService } from '../services/authentification.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html'
})
export class HomeComponent implements OnInit{

  currentUserLogin: string;
  
  constructor(private authentificationService: AuthentificationService) { }
  
  public ngOnInit() {
    if (this.authentificationService.getUserInfo()) {
      this.currentUserLogin = this.authentificationService.getUserInfo().identifiant;
    }
  }

}
