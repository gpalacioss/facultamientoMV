import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PermisoCuentaMontoComponent } from './permiso-cuenta-monto.component';

describe('PermisoCuentaMontoComponent', () => {
  let component: PermisoCuentaMontoComponent;
  let fixture: ComponentFixture<PermisoCuentaMontoComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PermisoCuentaMontoComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PermisoCuentaMontoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
