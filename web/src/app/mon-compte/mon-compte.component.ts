import { Component, OnInit } from '@angular/core';

import { User } from '../models/user.model';
import { AuthentificationService } from '../services/authentification.service';
import 'rxjs/add/operator/finally';

@Component({
  selector: 'app-mon-compte',
  templateUrl: './mon-compte.component.html'
})
export class MonCompteComponent implements OnInit {
  
  ready: boolean;
  
  user: User;
  loading: boolean;
  error: string;
  information: string;

  constructor(private authentificationService: AuthentificationService) { }

  ngOnInit() {
    this.user = new User();
    this.authentificationService.getInfoFromServer()
      .subscribe(res => {
        this.user = res;
        this.ready = true;
      }, err => {
        window.location.href = '/erreur';
      });
  }
  
  update() {
    this.error = undefined;
    this.information = undefined;
    
    if (!this.user.ancienPassword) {
      this.error = "Saisissez votre mot de passe pour modifier vos informations";
      return;
    }
    
    if (this.user.password && this.user.password !== this.user.passwordRepeat) {
      this.error = "Erreur dans la répétition du mot de passe";
      return;
    }
    
    let userSent: User = new User();
    Object.assign(userSent, this.user);
    userSent.passwordRepeat = undefined;
    
    this.loading = true;
    this.authentificationService.update(userSent)
      .finally(() => {
          this.loading = false;
      })
      .subscribe(
        ok => {
          this.information = "Information correctement modifiées";
          this.user.password = "";
          this.user.passwordRepeat = "";
          this.user.ancienPassword = "";
        },
        err => {
          this.error = "Impossible de modifier les informations du compte : " + err.error.message;
        });
  }

}
