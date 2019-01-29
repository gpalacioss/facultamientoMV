import { TestBed } from '@angular/core/testing';

import { CompaniaService } from './compania.service';

describe('CompaniaService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: CompaniaService = TestBed.get(CompaniaService);
    expect(service).toBeTruthy();
  });
});
