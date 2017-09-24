import { TestBed, inject } from '@angular/core/testing';

import { PoonaService } from './poona.service';

describe('PoonaService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [PoonaService]
    });
  });

  it('should be created', inject([PoonaService], (service: PoonaService) => {
    expect(service).toBeTruthy();
  }));
});
