import { TestBed, inject } from '@angular/core/testing';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';

import { HeaderService } from './header.service';

describe('HeaderService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [HeaderService],
      imports: [
        HttpClientModule
      ]
    });
  });

  it('should be created', inject([HeaderService], (service: HeaderService) => {
    expect(service).toBeTruthy();
  }));
});
