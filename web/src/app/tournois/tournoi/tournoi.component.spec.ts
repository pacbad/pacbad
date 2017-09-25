import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import {APP_BASE_HREF} from '@angular/common';
import { RouterModule, Routes } from '@angular/router';

import { TournoiService } from '../tournoi.service';

import { TournoiComponent } from './tournoi.component';

describe('TournoiComponent', () => {
  let component: TournoiComponent;
  let fixture: ComponentFixture<TournoiComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TournoiComponent ],
      providers: [
        TournoiService,
        {provide: APP_BASE_HREF, useValue : '/' }
      ],
      imports: [
        HttpClientModule,
        RouterModule.forRoot([])
      ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TournoiComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should be created', () => {
    expect(component).toBeTruthy();
  });
});
