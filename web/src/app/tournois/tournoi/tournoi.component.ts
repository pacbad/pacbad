import { Component, OnInit } from '@angular/core';
import { TournoiService } from '../tournoi.service';
import { Tournoi } from '../../models/tournoi.model';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-tournoi',
  templateUrl: './tournoi.component.html',
  styleUrls: ['./tournoi.component.css']
})
export class TournoiComponent implements OnInit {

  private sub:any;

  tournoiSelected: Tournoi;

  constructor(private tournoiService : TournoiService, private route:ActivatedRoute) { }

  ngOnInit() {
  	this.sub = this.route.params.subscribe(params => {
       let id = +params['id']; 

       	this.tournoiService.getTournoi(id).subscribe(
			(tournoi:Tournoi) => {
				this.tournoiSelected = tournoi;
			}
		);
    });
  }

  ngOnDestroy() {
    this.sub.unsubscribe();
  }

}
