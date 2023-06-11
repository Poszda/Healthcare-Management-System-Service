import { Component, Input, OnChanges, SimpleChanges } from '@angular/core';
import { ProfileTreatmentsHistory } from '../../models/profile.treatments-history.model';

@Component({
  selector: 'app-profile-treatments-history',
  templateUrl: './profile-treatments-history.component.html',
  styleUrls: ['./profile-treatments-history.component.css']
})
export class ProfileTreatmentsHistoryComponent implements OnChanges {
  @Input() data : ProfileTreatmentsHistory | undefined
  showEmptyLine1 : boolean = true;
  showEmptyLine2 : boolean = true;

  ngOnChanges(changes: SimpleChanges): void {
    if(this.data){
      if(this.data.medicationsLastThreeMonths.length > 0) this.showEmptyLine1 = false;
      if(this.data.medicationsLastSixMonths.length > 0) this.showEmptyLine2 = false;
    }
  }
}
