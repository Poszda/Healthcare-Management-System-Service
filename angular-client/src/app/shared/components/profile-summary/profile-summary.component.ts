import { Component,EventEmitter,Input,Output,OnChanges, SimpleChanges } from '@angular/core';

@Component({
  selector: 'app-profile-summary',
  templateUrl: './profile-summary.component.html',
  styleUrls: ['./profile-summary.component.css']
})
export class ProfileSummaryComponent implements OnChanges{
  @Input() allowEdit : boolean = false;
  @Output() editProfile : EventEmitter<any> = new EventEmitter();
  @Output() logOut : EventEmitter<any> = new EventEmitter();

  triggerEditProfile(){
    console.log('emiiterd')
    this.editProfile.emit();
  }

  triggerLogOut(){
    console.log('emiiterd')
    this.logOut.emit();
  }

  ngOnChanges(changes : SimpleChanges){
    if(changes['allowEdit']){
      console.log('changed')
    }
  }
}
