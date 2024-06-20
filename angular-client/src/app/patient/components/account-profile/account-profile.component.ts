import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { AlertService } from 'src/app/core/services/alert.service';
import { AuthService } from 'src/app/core/services/auth.service';
import { UserGlobalService } from 'src/app/core/services/user-global.service';
import { ProfileInformationCard } from 'src/app/shared/models/profile-information-card.model';
import { ProfileSummary } from 'src/app/shared/models/profile-summary.model';
import { UserService } from '../../services/user.service';
import * as moment from 'moment';
import { ProfileTreatmentsHistory } from 'src/app/shared/models/profile.treatments-history.model';
import { EditProfileFormComponent } from './edit-profile-form/edit-profile-form.component';
import { ConfirmationDialogComponent } from 'src/app/shared/components/confirmation-dialog/confirmation-dialog.component';
import { PatientEditProfileForm } from '../../models/patient-edit-profile-form.model';

@Component({
  selector: 'app-account-profile',
  templateUrl: './account-profile.component.html',
  styleUrls: ['./account-profile.component.css']
})
export class AccountProfileComponent implements OnInit{
  patientId : number = 0;
  physicalInformation : ProfileInformationCard | undefined  
  contactInformation : ProfileInformationCard | undefined
  summary : ProfileSummary | undefined
  medicalHistory : string[] | undefined
  tratamentsHistory : ProfileTreatmentsHistory | undefined
  profileData : any | undefined

  constructor(
    private dialog : MatDialog, 
    private authService : AuthService,
    private alertService : AlertService,
    private userGlobalService : UserGlobalService,
    private userService : UserService){}

  ngOnInit(): void {
    this.patientId = this.userService.getPatientIdFromLocalStorage()!;
    this.getPatientProfile();
  }

  getPatientProfile(){
      this.userGlobalService.getPatientProfile(this.patientId).subscribe(
        res =>{
         this.summary = this.obtainSummary(res);
         this.physicalInformation = this.obtainPhysicalInformation(res);
         this.contactInformation =  this.obtainContactInformation(res);
         this.medicalHistory = res.diagnostics
         this.tratamentsHistory = {medicationsLastSixMonths : res.medicationsLastSixMonths, medicationsLastThreeMonths : res.medicationsLastThreeMonths}
         this.profileData = res;
        },
        err =>{
          console.log(err)
        }
      );
    }

    obtainSummary(data : any){
      const list : string[] = [
        `${moment().diff(moment(data.patient.birthDate),'years')} years, ${'Male'}`,
      ]

      const result : ProfileSummary = {
        name: data.patient.user.firstName + " " + data.patient.user.lastName,
        rows: list,
        profileImage: data.profileImage
      }
      return result;
    }

    obtainContactInformation(data : any){
      const list = [
        {
          icon:'assets/icons/email-purple.png',
          value:data.patient.user.email
        },
        {
          icon:'assets/icons/phone-purple.png',
          value:data.patient.phone?data.patient.phone:'-'
        },
      ]
      const result : ProfileInformationCard = {
        title: 'Contact',
        rows: list
      };
      return result;
    }

    obtainPhysicalInformation(data : any){ //age blood height weight
      const age = data.patient.birthDate?moment().diff(moment(data.patient.birthDate),'years'): '-';
      const list = [
        {
          icon:'assets/icons/age-purple.png',
          value:age?age + " years": '-'
        },
        {
          icon:'assets/icons/blood-purple.png',
          value:data.patient.bloodType?data.patient.bloodType:'-'
        },
        {
          icon:'assets/icons/height-purple.png',
          value:data.patient.height?data.patient.height + " cm" : '-'
        },
        {
          icon:'assets/icons/weight-purple.png',
          value:data.patient.weight?data.patient.weight + " kg" : '-'
        },
      ]
      const result : ProfileInformationCard = {
        title: 'Physical Information',
        rows: list
      };
      return result;
    }

    obtainFormData(data : any){
      const result : PatientEditProfileForm = {
        firstName: data.patient.user.firstName,
        lastName: data.patient.user.lastName,
        height: data.patient.height,
        weight: data.patient.weight,
        bloodType:  data.patient.bloodType,
        phone: data.patient.phone,
        birthDate: moment(data.patient.birthDate).toDate()
      }
      return result;
    }


    editProfile(){
      this.dialog.open(EditProfileFormComponent, {
        panelClass: 'patient-appointment-form-dialog',
        maxHeight:'80vh',
        width:'600px',
        data : this.obtainFormData(this.profileData)
      }).afterClosed().subscribe(
        data =>{
          if(data){
            this.userService.updatePatientProfile(this.patientId,data).subscribe(
              res =>{
                this.getPatientProfile();
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


}
