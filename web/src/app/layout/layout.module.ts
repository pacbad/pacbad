import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';

import {ServicesModule} from '../services/services.module'

import { HeaderComponent } from './header/header.component';
import { FooterComponent } from './footer/footer.component';
import { LayoutCommunComponent } from './layout-commun/layout-commun.component';

@NgModule({
  imports: [
    CommonModule,
    RouterModule,
    ServicesModule
  ],
  declarations: [HeaderComponent, FooterComponent, LayoutCommunComponent],
  exports: [HeaderComponent, FooterComponent]
})
export class LayoutModule { }
