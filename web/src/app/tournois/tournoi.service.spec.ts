import { TestBed, inject } from '@angular/core/testing';

import { TournoiService } from './tournoi.service';

describe('TournoiService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [TournoiService]
    });
  });

  it('should be created', inject([TournoiService], (service: TournoiService) => {
    expect(service).toBeTruthy();
  }));
});
