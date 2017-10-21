import { Component, OnInit } from '@angular/core';
import { MdOptionSelectionChange } from '@angular/material';

import { AdminService } from './admin.service';
import { AuthentificationService } from '../services/authentification.service';
import { PoonaService } from '../services/poona.service';
import { User } from '../models/user.model';
import { Parametre } from '../models/parametre.model';
import { Instance } from '../models/instance.model';

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

  federation: Instance;
  ligues: Instance[];
  selectedLigue: Instance;
  txtLigue: string;
  codeps: Instance[];
  selectedCodep: Instance;
  txtCodep: string;
  clubs: Instance[];
  selectedClub: Instance;
  txtClub: string;

  constructor(
    private adminService: AdminService,
    private authentificationService: AuthentificationService,
    private poonaService: PoonaService
  ) {}

  ngOnInit() {
    this.adminService.getParametres().subscribe(
      res => {
        this.parametres = res;
        this.ready = true;
      },
      err => {
        window.location.href = '/erreur';
      }
    );
    this.listerAdministrateurs();
    this.currentUserLogin = this.authentificationService.getUserInfo().identifiant;

    this.poonaService.getFederation().subscribe(
      res => {
        this.federation = res;
      },
      err => {
        this.error =
          'Impossible de récupérer les informations de la fédération : ' +
          err.error.message;
      }
    );
    this.poonaService.getLigues().subscribe(
      res => {
        this.ligues = res;
      },
      err => {
        this.error =
          'Impossible de récupérer les ligues : ' + err.error.message;
      }
    );
  }

  private listerAdministrateurs() {
    this.adminService.getAdministrateurs().subscribe(
      res => {
        this.administrateurs = res;
        this.ready = true;
      },
      err => {
        window.location.href = '/erreur';
      }
    );
  }

  public deleteAdministrateur(user: User) {
    this.loading = true;
    this.adminService
      .deleteAdministrateur(user)
      .finally(() => {
        this.loading = false;
      })
      .subscribe(
        ok => {
          this.information = 'Administrateur correctement supprimé';
          this.listerAdministrateurs();
        },
        err => {
          this.error =
            "Impossible de supprimer l'administrateur : " + err.error.message;
        }
      );
  }

  public startAddAdmin() {
    this.newAdmin = new User();
  }

  public cancelAddAdmin() {
    this.newAdmin = undefined;
  }

  public validateAddAdmin() {
    this.adminService
      .addAdministrateur(this.newAdmin)
      .finally(() => {
        this.newAdmin = undefined;
        this.loading = false;
      })
      .subscribe(
        ok => {
          this.information = 'Administrateur correctement ajouté';
          this.listerAdministrateurs();
        },
        err => {
          this.error =
            "Impossible d'ajouter l'administrateur : " + err.error.message;
        }
      );
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
    this.adminService
      .updateParametre(this.editingParametre)
      .finally(() => {
        this.editingParametre = undefined;
        this.loading = false;
      })
      .subscribe(
        ok => {
          this.information = 'Paramètre correctement modifié';
        },
        err => {
          this.error =
            'Impossible de modifier le paramètre : ' + err.error.message;
        }
      );
  }

  public clearCache() {
    this.adminService
      .clearCache()
      .finally(() => {
        this.loading = false;
      })
      .subscribe(
        ok => {
          this.information = 'Cache vidé';
        },
        err => {
          this.error = 'Impossible de vider le cache : ' + err.error.message;
        }
      );
  }

  filterAutocomplete(listeInstances: Instance[], filtre: string): Instance[] {
    if (listeInstances && filtre) {
      return listeInstances.filter(
        instance =>
          instance.nom.toLowerCase().indexOf(filtre.toLowerCase()) >= 0
      );
    } else if (listeInstances) {
      return listeInstances;
    }
    return [];
  }

  public onLigueSelected(event: MdOptionSelectionChange, ligue: Instance) {
    if (event.source.selected) {
      this.selectedLigue = ligue;
      this.selectedCodep = undefined;
      this.txtCodep = undefined;
      this.selectedClub = undefined;
      this.txtClub = undefined;

      this.poonaService.getCodeps(ligue.id).subscribe(
        res => {
          this.codeps = res;
        },
        err => {
          this.error =
            'Impossible de récupérer les codeps : ' + err.error.message;
        }
      );
    }
  }

  public onCodepSelected(event: MdOptionSelectionChange, codep: Instance) {
    if (event.source.selected) {
      this.selectedCodep = codep;
      this.selectedClub = undefined;
      this.txtClub = undefined;

      this.poonaService.getClubs(this.selectedLigue.id, codep.id).subscribe(
        res => {
          this.clubs = res;
        },
        err => {
          this.error =
            'Impossible de récupérer les clubs : ' + err.error.message;
        }
      );
    }
  }

  public onClubSelected(event: MdOptionSelectionChange, club: Instance) {
    if (event.source.selected) {
      this.selectedClub = club;

      this.poonaService.getClub(club.id).subscribe(
        res => {
          console.log('Club sélectionné', club);
        },
        err => {
          this.error =
            'Impossible de récupérer les informations du club : ' +
            err.error.message;
        }
      );
    }
  }
}
