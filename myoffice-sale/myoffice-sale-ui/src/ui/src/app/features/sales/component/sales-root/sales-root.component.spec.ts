import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SalesRootComponent } from './sales-root.component';

describe('SalesRootComponent', () => {
  let component: SalesRootComponent;
  let fixture: ComponentFixture<SalesRootComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SalesRootComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SalesRootComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
