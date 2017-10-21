import {Component, OnInit} from '@angular/core';
import {Meta, MetaDefinition} from '@angular/platform-browser';

import {VersionService} from './services/version.service';
import {AuthentificationService} from './services/authentification.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html'
})
export class AppComponent implements OnInit {
  private title: string = 'pacbad';

  constructor(
    private versionService: VersionService,
    private authentificationService: AuthentificationService,
    private meta: Meta
  ) {}

  ngOnInit() {
    // Validation du jeton si existant
    this.authentificationService.validateToken();

    this.versionService.getVersion().subscribe((data: string) => {
      this.meta.addTag({name: 'back.version', value: data});
    });
  }
}
