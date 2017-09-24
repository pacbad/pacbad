import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { VersionService } from './version.service';
import {AuthentificationService} from './authentification.service';
import {PoonaService} from './poona.service';

@NgModule({
  imports: [
    CommonModule
  ],
  declarations: [],
  providers: [VersionService, AuthentificationService, PoonaService]
})
export class ServicesModule { }
