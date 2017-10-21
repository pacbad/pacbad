import { Component } from '@angular/core';
import { Location } from '@angular/common';

@Component({
  selector: 'app-erreur',
  templateUrl: './erreur.component.html'
})
export class ErreurComponent {
  constructor(private location: Location) {}

  back() {
    this.location.back();
  }
}
