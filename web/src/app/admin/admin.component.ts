import { Component, OnInit } from '@angular/core';

import { AdminService } from './admin.service';
import { AuthentificationService } from '../services/authentification.service';
import { User } from '../models/user.model';
import { Parametre } from '../models/parametre.model';

@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html'
})
export class AdminComponent implements OnInit {

  ready: boolean;
  
  loading: boolean;
  error: string;
  information: string;
  
  currentUserLogin: string;
  parametres: Parametre[];
  administrateurs: User[];
  
  newAdmin: User;
  editingParametre: Parametre;
  
  constructor(private adminService: AdminService, private authentificationService: AuthentificationService) { }

  ngOnInit() {
    this.adminService.getParametres()
      .subscribe(res => {
        this.parametres = res;
        this.ready = true;
      }, err => {
        window.location.href = '/erreur';
      });
    this.listerAdministrateurs();
    this.currentUserLogin = this.authentificationService.getUserInfo().identifiant;
  }
  
  private listerAdministrateurs() {
    this.adminService.getAdministrateurs()
      .subscribe(res => {
        this.administrateurs = res;
        this.ready = true;
      }, err => {
        window.location.href = '/erreur';
      });
    
  }
  
  public deleteAdministrateur(user: User) {
    this.loading = true;
    this.adminService.deleteAdministrateur(user)
      .finally(() => {
        this.loading = false;
      })
      .subscribe(
        ok => {
          this.information = "Administrateur correctement supprimé";
          this.listerAdministrateurs();
        },
        err => {
          this.error = "Impossible de supprimer l'administrateur : " + err.error.message;
        });
  }
  
  public startAddAdmin() {
    this.newAdmin = new User();
  }
  
  public cancelAddAdmin() {
    this.newAdmin = undefined;
  }
  
  public validateAddAdmin() {
    this.adminService.addAdministrateur(this.newAdmin)
      .finally(() => {
        this.newAdmin = undefined;
        this.loading = false;
      })
      .subscribe(
        ok => {
          this.information = "Administrateur correctement ajouté";
          this.listerAdministrateurs();
        },
        err => {
          this.error = "Impossible d'ajouter l'administrateur : " + err.error.message;
        });
  }
  
  public editParametre(parametre: Parametre) {
    if (!this.editingParametre) {
      this.editingParametre = parametre;
    }
  }
  
  public cancelParametre() {
    this.editingParametre = undefined;
  }
  
  public validateParametre() {
    this.adminService.updateParametre(this.editingParametre)
      .finally(() => {
        this.editingParametre = undefined;
        this.loading = false;
      })
      .subscribe(
        ok => {
          this.information = "Paramètre correctement modifié";
        },
        err => {
          this.error = "Impossible de modifier le paramètre : " + err.error.message;
        });
  }
  
  public clearCache() {
    this.adminService.clearCache()
      .finally(() => {
        this.loading = false;
      })
      .subscribe(
        ok => {
          this.information = "Cache vidé";
        },
        err => {
          this.error = "Impossible de vider le cache : " + err.error.message;
        });;
  }

}
