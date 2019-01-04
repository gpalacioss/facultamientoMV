import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PermisosUsuarioComponent } from './permisos-usuario.component';

describe('PermisosUsuarioComponent', () => {
  let component: PermisosUsuarioComponent;
  let fixture: ComponentFixture<PermisosUsuarioComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PermisosUsuarioComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PermisosUsuarioComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
