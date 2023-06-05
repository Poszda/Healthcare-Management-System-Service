import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ProfileTreatmentsHistoryComponent } from './profile-treatments-history.component';

describe('ProfileTreatmentsHistoryComponent', () => {
  let component: ProfileTreatmentsHistoryComponent;
  let fixture: ComponentFixture<ProfileTreatmentsHistoryComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ProfileTreatmentsHistoryComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ProfileTreatmentsHistoryComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
