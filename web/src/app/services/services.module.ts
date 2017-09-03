import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { VersionService } from './version.service';
import {AuthentificationService} from './authentification.service';

@NgModule({
  imports: [
    CommonModule
  ],
  declarations: [],
  providers: [VersionService, AuthentificationService]
})
export class ServicesModule { }
