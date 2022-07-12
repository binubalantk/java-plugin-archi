import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AngularPluginInterfacesComponent } from './angular-plugin-interfaces.component';

describe('AngularPluginInterfacesComponent', () => {
  let component: AngularPluginInterfacesComponent;
  let fixture: ComponentFixture<AngularPluginInterfacesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AngularPluginInterfacesComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AngularPluginInterfacesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
