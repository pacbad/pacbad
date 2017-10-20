import { Component, OnInit } from '@angular/core';
import { TournoiService } from '../tournoi.service';
import { Tournoi } from '../../models/tournoi.model';
import { ActivatedRoute } from '@angular/router';
import { Title } from '@angular/platform-browser';

@Component({
  selector: 'app-tournoi',
  templateUrl: './tournoi.component.html'
})
export class TournoiComponent implements OnInit {

  private sub:any;

  tournoiSelected: Tournoi;

  constructor(private tournoiService : TournoiService, private title: Title, private route:ActivatedRoute) { }

  ngOnInit() {
  	this.sub = this.route.params.subscribe(params => {
       let id = +params['id']; 

       	this.tournoiService.getTournoi(id).subscribe(
			(tournoi:Tournoi) => {
				this.tournoiSelected = tournoi;
				this.title.setTitle(this.tournoiSelected.nom + ' | PacBad');
			}
		);
    });
  }

  ngOnDestroy() {
    this.sub.unsubscribe();
  }

}
