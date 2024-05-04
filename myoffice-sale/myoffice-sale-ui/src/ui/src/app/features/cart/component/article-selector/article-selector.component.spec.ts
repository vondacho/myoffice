import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ArticleSelectorComponent } from './article-selector.component';

describe('ArticleSelectorComponent', () => {
  let component: ArticleSelectorComponent;
  let fixture: ComponentFixture<ArticleSelectorComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ArticleSelectorComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ArticleSelectorComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
