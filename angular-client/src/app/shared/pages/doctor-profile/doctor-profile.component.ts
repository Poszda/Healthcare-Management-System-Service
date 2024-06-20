import { Component,OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { ProfileInformationCard } from '../../models/profile-information-card.model';
import { ProfileSummary } from '../../models/profile-summary.model';
import * as moment from 'moment';
import { UserGlobalService } from 'src/app/core/services/user-global.service';

@Component({
  selector: 'app-doctor-profile',
  templateUrl: './doctor-profile.component.html',
  styleUrls: ['./doctor-profile.component.css']
})
export class DoctorProfileComponent implements OnInit {

  locationInformation : ProfileInformationCard | undefined  
  programAndContactInformation : ProfileInformationCard | undefined
  summary : ProfileSummary | undefined
  description : string | undefined
  university : string | undefined

  constructor( private route: ActivatedRoute,private userGlobalService : UserGlobalService){}

  ngOnInit(): void {
    this.route.params.subscribe(
      res =>{
        this.getDoctorProfile(res['id']);
      },
      err =>{
        console.log(err)
      }
    )
  }


  getDoctorProfile(doctorId : number){
    this.userGlobalService.getDoctorProfile(doctorId).subscribe(
      res =>{
        this.locationInformation = this.obtainLocationInformation(res);
        this.programAndContactInformation = this.obtainProgramAndContact(res);
        this.summary = this.obtainSummary(res);
        this.description = res.description
        this.university = res.university
      },
      err =>{
        console.log(err)
      }
    );
  }

  obtainSummary(data : any){
    const list : string[] = [
      `Doctor specialised in ${data.speciality.name} at "${data.hospital.name}"`,
      `Graduated from "${data.university}"`
    ]
    if(!data.university) list.pop();
    
    const result : ProfileSummary = {
      name: data.user.firstName + " " + data.user.lastName,
      rows: list,
      profileImage: data.profileImage
    }
    return result;
  }

  obtainLocationInformation(data : any){
    const hospital = data.hospital;
    const list = [
      {
        icon:'assets/icons/hospital-purple.png',
        value:hospital.name
      },
      {
        icon:'assets/icons/location-purple.png',
        value:hospital.county
      },
      {
        icon:'assets/icons/locality-purple.png',
        value:hospital.locality
      }
    ]
    const result : ProfileInformationCard = {
      title: 'Location',
      rows: list
    };

    return result;
  }

  obtainProgramAndContact(data : any){
    const list = [
      {
        icon:'assets/icons/program-purple.png',
        value:`${moment(data.programStart, "HH:mm:ss").format("HH:mm")} - ${moment(data.programEnd, "HH:mm:ss").format("HH:mm")}`
      },
      {
        icon:'assets/icons/email-purple.png',
        value:data.user.email
      },
    ]

    const result  : ProfileInformationCard = {
      title: 'Program & Contact',
      rows: list
    }
    return result
  }

}
