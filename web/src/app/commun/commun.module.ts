import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ChargementEcranComponent } from './chargement-ecran/chargement-ecran.component';
import { ChargementMaskComponent } from './chargement-mask/chargement-mask.component';

@NgModule({
  imports: [CommonModule],
  declarations: [ChargementEcranComponent, ChargementMaskComponent],
  exports: [ChargementEcranComponent, ChargementMaskComponent]
})
export class CommunModule {}
