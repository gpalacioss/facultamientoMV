import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { FormPermisoCuentaComponent } from './form-permiso-cuenta.component';

describe('FormPermisoCuentaComponent', () => {
  let component: FormPermisoCuentaComponent;
  let fixture: ComponentFixture<FormPermisoCuentaComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ FormPermisoCuentaComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(FormPermisoCuentaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
