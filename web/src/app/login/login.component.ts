import { Component, OnInit, AfterViewInit, ViewChildren } from '@angular/core';

import { User } from '../models/user.model';
import { AuthentificationService } from '../services/authentification.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html'
})
export class LoginComponent implements OnInit, AfterViewInit {
  
  @ViewChildren('txtMail') txtMail;
  
  user: User;
  loading: boolean;
  error: boolean;

  constructor(private authentificationService: AuthentificationService) { }
  
  ngOnInit() {
    this.user = new User();
  }
  
  ngAfterViewInit() {
    this.txtMail.first.nativeElement.focus();
  }
  
  public login() {
    this.loading = true;
    this.authentificationService.login(this.user)
      .subscribe(
        ok => {
          this.loading = false;
          window.location.href = '/';
        },
        err => {
          this.loading = false;
          this.error = true;
        });
  }

}
