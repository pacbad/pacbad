import { Component, OnInit } from '@angular/core';
import {Observable} from 'rxjs/Observable';

import { VersionService } from '../../services/version.service';

@Component({
  selector: 'app-footer',
  templateUrl: './footer.component.html'
})
export class FooterComponent implements OnInit {

  private version: string;

  constructor(private versionService: VersionService) { }

  ngOnInit() {
    this.version = '?';
    /*this.versionService.getVersion().subscribe((data: string) => {
      this.version = data;
    });*/
  }

}
