import { TestBed, inject } from '@angular/core/testing';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';

import { PoonaService } from './poona.service';

describe('PoonaService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [PoonaService],
      imports: [HttpClientModule]
    });
  });

  it(
    'should be created',
    inject([PoonaService], (service: PoonaService) => {
      expect(service).toBeTruthy();
    })
  );
});
