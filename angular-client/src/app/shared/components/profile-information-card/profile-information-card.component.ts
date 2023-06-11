import { Component,Input} from '@angular/core';
import { ProfileInformationCard } from '../../models/profile-information-card.model';

@Component({
  selector: 'app-profile-information-card',
  templateUrl: './profile-information-card.component.html',
  styleUrls: ['./profile-information-card.component.css']
})
export class ProfileInformationCardComponent {
  @Input() data : ProfileInformationCard |undefined
}
