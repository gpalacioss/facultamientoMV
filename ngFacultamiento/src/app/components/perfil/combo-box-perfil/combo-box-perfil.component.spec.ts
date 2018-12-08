import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ComboBoxPerfilComponent } from './combo-box-perfil.component';

describe('ComboBoxPerfilComponent', () => {
  let component: ComboBoxPerfilComponent;
  let fixture: ComponentFixture<ComboBoxPerfilComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ComboBoxPerfilComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ComboBoxPerfilComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
