import { Component,EventEmitter,Input,Output,OnChanges, SimpleChanges } from '@angular/core';
import { ProfileSummary } from '../../models/profile-summary.model';

@Component({
  selector: 'app-profile-summary',
  templateUrl: './profile-summary.component.html',
  styleUrls: ['./profile-summary.component.css']
})
export class ProfileSummaryComponent implements OnChanges{
  @Input() allowEdit : boolean = false;
  @Input() summary : ProfileSummary | undefined;
  @Output() editProfile : EventEmitter<any> = new EventEmitter();
  @Output() logOut : EventEmitter<any> = new EventEmitter();

  triggerEditProfile(){
    this.editProfile.emit();
  }

  triggerLogOut(){
    this.logOut.emit();
  }

  ngOnChanges(changes : SimpleChanges){
    if(changes['allowEdit']){
    }
  }
}
