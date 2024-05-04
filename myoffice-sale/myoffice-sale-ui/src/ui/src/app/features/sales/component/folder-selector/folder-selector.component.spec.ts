import {async, ComponentFixture, TestBed} from '@angular/core/testing';
import {FolderSelectorComponent} from './folder-selector.component';

describe('FolderSelectorComponent', () => {
  let component: FolderSelectorComponent;
  let fixture: ComponentFixture<FolderSelectorComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [FolderSelectorComponent]
    })
      .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(FolderSelectorComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
