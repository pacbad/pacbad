import { Component, OnInit } from '@angular/core';

import { User } from '../models/user.model';
import { AuthentificationService } from '../services/authentification.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html'
})
export class LoginComponent implements OnInit {
  
  user: User;
  loading: boolean;

  constructor(private authentificationService: AuthentificationService) { }
  
  ngOnInit() {
    this.user = new User();
  }
  
  public connecter() {
    this.loading = true;
    this.authentificationService.login(this.user.login, this.user.password).subscribe(ok => console.log('Authent ok', ok), err => console.log('Erreur d authentification', err));
    console.log('login');
  }

}
