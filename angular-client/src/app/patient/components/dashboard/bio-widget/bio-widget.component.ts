import { Component, Input, OnInit } from '@angular/core';
import * as moment from 'moment';
import { Patient } from 'src/app/core/models/patient.model';
import { PatientBio } from 'src/app/patient/models/patient-bio.model';
import { UserService } from 'src/app/patient/services/user.service';

@Component({
  selector: 'app-bio-widget',
  templateUrl: './bio-widget.component.html',
  styleUrls: ['./bio-widget.component.css']
})
export class BioWidgetComponent implements OnInit {
  @Input() data : PatientBio | undefined
  user : any

  constructor(private userService : UserService){}
  ngOnInit(): void {
    this.user = this.userService.getUserFromLocalStorage();
  }

  getAge(birthDate : string | undefined){
    if(birthDate) return moment().diff(moment(birthDate),'years')
    return '-'
  }
}
