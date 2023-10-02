import { Component } from '@angular/core';
import { ProfileInformationCard } from '../../models/profile-information-card.model';
import { ProfileSummary } from '../../models/profile-summary.model';
import { ProfileTreatmentsHistory } from '../../models/profile.treatments-history.model';
import { UserGlobalService } from 'src/app/core/services/user-global.service';
import * as moment from 'moment';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-patient-profile',
  templateUrl: './patient-profile.component.html',
  styleUrls: ['./patient-profile.component.css']
})
export class PatientProfileComponent {
  patientId : number = 0;
  physicalInformation : ProfileInformationCard | undefined  
  contactInformation : ProfileInformationCard | undefined
  summary : ProfileSummary | undefined
  medicalHistory : string[] | undefined
  tratamentsHistory : ProfileTreatmentsHistory | undefined

  constructor(
    private route: ActivatedRoute,
    private userGlobalService : UserGlobalService){}

  ngOnInit(): void {
    this.route.params.subscribe(
      res =>{
        this.getPatientProfile(res['id']);
      },
      err =>{
        console.log(err)
      }
    )
  }

  getPatientProfile(patientId : number){
      this.userGlobalService.getPatientProfile(patientId).subscribe(
        res =>{
         this.summary = this.obtainSummary(res);
         this.physicalInformation = this.obtainPhysicalInformation(res);
         this.contactInformation =  this.obtainContactInformation(res);
         this.medicalHistory = res.diagnostics
         this.tratamentsHistory = {medicationsLastSixMonths : res.medicationsLastSixMonths, medicationsLastThreeMonths : res.medicationsLastThreeMonths}
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
        rows: list
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
          value:data.patient.phone
        },
      ]
      const result : ProfileInformationCard = {
        title: 'Contact',
        rows: list
      };
      return result;
    }

    obtainPhysicalInformation(data : any){
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
}
