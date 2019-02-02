import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { FormPermisoComponent } from './form-permiso.component';

describe('FormPermisoComponent', () => {
  let component: FormPermisoComponent;
  let fixture: ComponentFixture<FormPermisoComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ FormPermisoComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(FormPermisoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
