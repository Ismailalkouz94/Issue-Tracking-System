import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CustomModalComponent } from './custom-modal.component';

describe('CustomModalComponent', () => {
  let component: CustomModalComponent;
  let fixture: ComponentFixture<CustomModalComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CustomModalComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CustomModalComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
