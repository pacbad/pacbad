import { Component, OnInit } from '@angular/core';
import { TournoiService } from './tournoi.service';

@Component({
  selector: 'app-tournois',
  templateUrl: './tournois.component.html'
})
export class TournoisComponent implements OnInit {
	ready: boolean;
	tournois : any;

  	constructor(private tournoiService : TournoiService) { }

	ngOnInit() {
		this.ready = true;
		this.tournoiService.getTournois().subscribe(
        tournois => {
          this.tournois = tournois;
        });
	}

}
