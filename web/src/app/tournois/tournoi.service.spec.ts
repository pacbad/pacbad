import { TestBed, inject } from '@angular/core/testing';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';

import { TournoiService } from './tournoi.service';

describe('TournoiService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [TournoiService],
      imports: [
        HttpClientModule
      ]
    });
  });

  it('should be created', inject([TournoiService], (service: TournoiService) => {
    expect(service).toBeTruthy();
  }));
});
