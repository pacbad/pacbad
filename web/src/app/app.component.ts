import { Component, OnInit } from '@angular/core';
import { Meta, MetaDefinition } from '@angular/platform-browser';

import { VersionService } from './services/version.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html'
})
export class AppComponent implements OnInit {
  private title: string = 'pacbad';

  constructor(private versionService: VersionService, private meta: Meta) {
  }
  
  ngOnInit() {
    this.versionService.getVersion().subscribe((data: string) => {
      this.meta.addTag({name: 'back.version', value: data});
    });
  }
}
