import { Component, OnInit } from '@angular/core';
import { TournoiService } from './tournoi.service';

@Component({
  selector: 'app-tournois',
  templateUrl: './tournois.component.html'
})
export class TournoisComponent implements OnInit {

	tournois : any;

  	constructor(private tournoiService : TournoiService) { }

	ngOnInit() {
		this.tournoiService.getTournois().subscribe(
        tournois => {
          this.tournois = tournois;
        });
	}

}
