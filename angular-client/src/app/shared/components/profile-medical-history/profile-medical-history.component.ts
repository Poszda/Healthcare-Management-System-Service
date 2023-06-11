import { Component,Input } from '@angular/core';

@Component({
  selector: 'app-profile-medical-history',
  templateUrl: './profile-medical-history.component.html',
  styleUrls: ['./profile-medical-history.component.css']
})
export class ProfileMedicalHistoryComponent {
@Input() data : string[] | undefined

}
