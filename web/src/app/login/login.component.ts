import { Component, OnInit, AfterViewInit, ViewChildren } from '@angular/core';

import { User } from '../models/user.model';
import { AuthentificationService } from '../services/authentification.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html'
})
export class LoginComponent implements OnInit, AfterViewInit {
  
  @ViewChildren('txtLogin') txtLogin;
  
  user: User;
  loading: boolean;
  error: boolean;

  constructor(private authentificationService: AuthentificationService) { }
  
  ngOnInit() {
    this.user = new User();
  }
  
  ngAfterViewInit() {
    this.txtLogin.first.nativeElement.focus();
  }
  
  public connecter() {
    this.loading = true;
    this.authentificationService.login(this.user)
      .subscribe(
        ok => {
          console.log('Authent ok', ok);
          this.loading = false;
          window.location.href = '/';
        },
        err => {
          console.log('Erreur d authentification', err);
          this.loading = false;
          this.error = true;
        });
    console.log('login');
  }

}
