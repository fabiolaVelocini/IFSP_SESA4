import { TestBed } from '@angular/core/testing';

import { ExternalTokenGuard } from './external-token.guard';

describe('ExternalTokenGuard', () => {
  let guard: ExternalTokenGuard;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    guard = TestBed.inject(ExternalTokenGuard);
  });

  it('should be created', () => {
    expect(guard).toBeTruthy();
  });
});
