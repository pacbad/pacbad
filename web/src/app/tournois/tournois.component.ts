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
		this.tournoiService.getTournois()
		  .subscribe(
        tournois => {
		      this.ready = true;
          this.tournois = tournois;
        }, err => {
        window.location.href = '/erreur';
      });
	}

}
