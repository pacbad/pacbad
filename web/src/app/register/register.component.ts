import { Component, OnInit, AfterViewInit, ViewChildren } from '@angular/core';

import { User } from '../models/user.model';
import { AuthentificationService } from '../services/authentification.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html'
})
export class RegisterComponent implements OnInit, AfterViewInit {

  @ViewChildren('txtIdentifiant') txtIdentifiant;
  
  user: User;
  loading: boolean;
  error: string;

  constructor(private authentificationService: AuthentificationService) { }

  ngOnInit() {
    this.user = new User();
  }
  
  ngAfterViewInit() {
    this.txtIdentifiant.first.nativeElement.focus();
  }
  
  public register() {
    this.error = undefined;
    
    if (this.user.password !== this.user.passwordRepeat) {
      this.error = "Erreur dans la répétition du mot de passe";
      return;
    }
    
    let userSent: User = new User();
    Object.assign(userSent, this.user);
    userSent.passwordRepeat = undefined;
    
    this.loading = true;
    this.authentificationService.register(userSent)
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
          this.error = JSON.stringify(err);
        });
  }

}
