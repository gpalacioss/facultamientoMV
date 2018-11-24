import { TestBed } from '@angular/core/testing';

import { FacultadService } from './facultad.service';

describe('FacultadService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: FacultadService = TestBed.get(FacultadService);
    expect(service).toBeTruthy();
  });
});
