import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-tournois',
  templateUrl: './tournois.component.html'
})
export class TournoisComponent implements OnInit {
  
  ready: boolean;

  constructor() { }
  
  ngOnInit() {
    this.ready = true;
  }

}
