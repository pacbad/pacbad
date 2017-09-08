import { Component, OnInit, AfterViewInit, ViewChildren } from '@angular/core';

import { User } from '../models/user.model';
import { AuthentificationService } from '../services/authentification.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html'
})
export class LoginComponent implements OnInit, AfterViewInit {
  
  @ViewChildren('txtIdentifiant') txtIdentifiant;
  
  user: User;
  loading: boolean;
  error: boolean;

  constructor(private authentificationService: AuthentificationService) { }
  
  ngOnInit() {
    this.user = new User();
  }
  
  ngAfterViewInit() {
    this.txtIdentifiant.first.nativeElement.focus();
  }
  
  public login() {
    this.loading = true;
    this.authentificationService.login(this.user)
      .finally(
        () => {
          this.loading = false;
        }
      )
      .subscribe(
        ok => {
          window.location.href = '/';
        },
        err => {
          this.error = true;
        });
  }

}
