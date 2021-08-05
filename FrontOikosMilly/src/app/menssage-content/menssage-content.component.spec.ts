import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MenssageContentComponent } from './menssage-content.component';

describe('MenssageContentComponent', () => {
  let component: MenssageContentComponent;
  let fixture: ComponentFixture<MenssageContentComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ MenssageContentComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(MenssageContentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
