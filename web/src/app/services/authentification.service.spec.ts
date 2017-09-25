import { TestBed, inject } from '@angular/core/testing';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';

import { AuthentificationService } from './authentification.service';

describe('AuthentificationService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [AuthentificationService],
      imports: [
        HttpClientModule
      ]
    });
  });

  it('should be created', inject([AuthentificationService], (service: AuthentificationService) => {
    expect(service).toBeTruthy();
  }));
});
