import { TestBed, inject } from '@angular/core/testing';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';

import { VersionService } from './version.service';

describe('VersionService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [VersionService],
      imports: [HttpClientModule]
    });
  });

  it(
    'should be created',
    inject([VersionService], (service: VersionService) => {
      expect(service).toBeTruthy();
    })
  );
});
