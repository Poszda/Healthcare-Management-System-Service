import { Component ,Input} from '@angular/core';
import { HospitalOverview } from 'src/app/admin/models/hospital-overview.model';

@Component({
  selector: 'app-activity-overview-widget',
  templateUrl: './activity-overview-widget.component.html',
  styleUrls: ['./activity-overview-widget.component.css']
})
export class ActivityOverviewWidgetComponent {
 @Input() overview : HospitalOverview | undefined
}
