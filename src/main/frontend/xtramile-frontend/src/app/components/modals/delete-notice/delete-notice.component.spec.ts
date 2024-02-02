import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DeleteNoticeComponent } from './delete-notice.component';

describe('DeleteNoticeComponent', () => {
  let component: DeleteNoticeComponent;
  let fixture: ComponentFixture<DeleteNoticeComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [DeleteNoticeComponent]
    });
    fixture = TestBed.createComponent(DeleteNoticeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
