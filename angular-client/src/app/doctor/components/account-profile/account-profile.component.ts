import { Component,OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { AlertService } from 'src/app/core/services/alert.service';
import { AuthService } from 'src/app/core/services/auth.service';
import { EditProfileFormComponent } from './edit-profile-form/edit-profile-form.component';
import { ConfirmationDialogComponent } from 'src/app/shared/components/confirmation-dialog/confirmation-dialog.component';
import { UserGlobalService } from 'src/app/core/services/user-global.service';
import { DoctorService } from '../../services/doctor.service';
import { ProfileInformationCard } from 'src/app/shared/models/profile-information-card.model';
import * as moment from 'moment';
import { ProfileSummary } from 'src/app/shared/models/profile-summary.model';

@Component({
  selector: 'app-account-profile',
  templateUrl: './account-profile.component.html',
  styleUrls: ['./account-profile.component.css']
})
export class AccountProfileComponent implements OnInit {
  doctorId : number = 0;
  locationInformation : ProfileInformationCard | undefined  
  programAndContactInformation : ProfileInformationCard | undefined
  summary : ProfileSummary | undefined
  description : string | undefined
  university : string | undefined
  profileImage : string | undefined | null

  constructor(
    private dialog : MatDialog, 
    private authService : AuthService,
    private alertService : AlertService,
    private userGlobalService : UserGlobalService,
    private doctorService : DoctorService){}

  ngOnInit(): void {
    this.doctorId = this.doctorService.getDoctorFromLocalStorage().id;
    this.getDoctorProfile();
  }

  getDoctorProfile(){
    this.userGlobalService.getDoctorProfile(this.doctorId).subscribe(
      res =>{
        this.locationInformation = this.obtainLocationInformation(res);
        this.programAndContactInformation = this.obtainProgramAndContact(res);
        this.summary = this.obtainSummary(res);
        this.description = res.description
        this.university = res.university
        this.profileImage = res.profileImage
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
      name: "Dr. " + data.user.firstName + " " + data.user.lastName,
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

  logOut(){
    this.dialog.open(ConfirmationDialogComponent,{
      maxHeight:'80vh',
      width:'600px',
      data : {message : "Are you sure you want to log out?"}
    }).afterClosed().subscribe(
      succes =>{
        if(succes === true){
          this.authService.logout();
        }
      }
    )

  }

  editProfile(){
    this.dialog.open(EditProfileFormComponent, {
      panelClass: 'patient-appointment-form-dialog',
      maxHeight:'80vh',
      width:'600px',
      data : {
        university : this.university,
        description : this.description
      }
    }).afterClosed().subscribe(
      data =>{
        if(data){
          this.doctorService.updateDoctorProfile(this.doctorId,data).subscribe(
            res =>{
              this.description = res.description
              this.updateUniversity(res.university);
              this.updateProfileImage(res.profileImage);
              this.alertService.showSuccess('Profile updated succesfully');
            },
            err =>{
              console.log(err)
              this.alertService.showError(err);
            }
          )
          
        }
      }
    )
  }

  updateUniversity(university : string){
    if(this.summary)this.summary.rows[1] = `Graduated from "${university}"`;
    this.university = university;
  }

  updateProfileImage(profileImage : string){
    if(this.summary)this.summary.profileImage = profileImage;
    this.profileImage = profileImage;
  }
}
