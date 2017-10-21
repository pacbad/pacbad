import { Component, OnInit, AfterViewInit, ViewChildren } from '@angular/core';
import { ActivatedRoute, Params } from '@angular/router';

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
  error: string;

  redirectUri: string;

  constructor(
    private activatedRoute: ActivatedRoute,
    private authentificationService: AuthentificationService
  ) {}

  ngOnInit() {
    this.user = new User();

    this.activatedRoute.queryParams.subscribe((queryParams: Params) => {
      this.redirectUri = queryParams['redirect'];
    });
  }

  ngAfterViewInit() {
    this.txtIdentifiant.first.nativeElement.focus();
  }

  public login() {
    this.loading = true;
    this.authentificationService
      .login(this.user)
      .finally(() => {
        this.loading = false;
      })
      .subscribe(
        ok => {
          let redirect: string = '/';
          if (this.redirectUri && this.redirectUri.indexOf('/login') < 0) {
            redirect = this.redirectUri;
          }
          window.location.href = redirect;
        },
        err => {
          this.error = JSON.parse(err.error).message;
        }
      );
  }
}
