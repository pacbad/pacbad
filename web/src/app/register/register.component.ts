import { Component, OnInit, AfterViewInit, ViewChildren } from '@angular/core';

import { User } from '../models/user.model';
import { AuthentificationService } from '../services/authentification.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html'
})
export class RegisterComponent implements OnInit, AfterViewInit {

  @ViewChildren('txtLicence') txtLicence;
  
  user: User;
  loading: boolean;
  error: string;

  constructor(private authentificationService: AuthentificationService) { }

  ngOnInit() {
    this.user = new User();
  }
  
  ngAfterViewInit() {
    this.txtLicence.first.nativeElement.focus();
  }
  
  public register() {
    // TODO Vérification du mot de passe répété
    
    this.loading = true;
    this.authentificationService.register(this.user)
      .subscribe(
        ok => {
          this.loading = false;
          window.location.href = '/';
        },
        err => {
          this.loading = false;
          this.error = err;
        });
  }

}
