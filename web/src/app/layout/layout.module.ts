import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';

import { ServicesModule } from '../services/services.module';

import { HeaderComponent } from './header/header.component';
import { FooterComponent } from './footer/footer.component';
import { LayoutCommunComponent } from './layout-commun/layout-commun.component';

import { HeaderService } from './header/header.service';

@NgModule({
  imports: [CommonModule, RouterModule, ServicesModule],
  declarations: [HeaderComponent, FooterComponent, LayoutCommunComponent],
  exports: [HeaderComponent, FooterComponent],
  providers: [HeaderService]
})
export class LayoutModule {}
