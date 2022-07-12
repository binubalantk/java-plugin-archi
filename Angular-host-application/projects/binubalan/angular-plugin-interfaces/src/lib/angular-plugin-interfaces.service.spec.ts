import { TestBed } from '@angular/core/testing';

import { AngularPluginInterfacesService } from './angular-plugin-interfaces.service';

describe('AngularPluginInterfacesService', () => {
  let service: AngularPluginInterfacesService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(AngularPluginInterfacesService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
