import { formatDate } from '@angular/common';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import * as moment from 'moment';
import { Patient } from 'src/app/core/models/patient.model';
import { DoctorSearch } from '../models/doctor-search.model';
import { DoctorSuggestionInfo } from '../models/doctor-suggestion-info.model';
import { PatientBio } from '../models/patient-bio.model';
import { environment } from 'src/environments/environment';

@Injectable()
export class UserService {

  private baseUrl = environment.apiUrl;
  constructor(private http: HttpClient) { }

  getPatientIdFromLocalStorage() : number | null{
    return localStorage.getItem('patient')? Number(JSON.parse(localStorage.getItem('patient')!).id) : null;
  }

  getUserFromLocalStorage() : any{
    return localStorage.getItem('patient')? JSON.parse(localStorage.getItem('user')!) : null;
  }

  getPatientBio(patientId : number){
    return this.http.get<PatientBio>(`${this.baseUrl}/api/users/getPatientBio/${patientId}`);
  }

  getDoctorsById(ids : number[]){
    return this.http.get<any>(`${this.baseUrl}/api/users/getDoctorsById/${ids}`);
  }

  getDoctorsSuggestionInfo(ids : number[]){
    return this.http.get<DoctorSuggestionInfo[]>(`${this.baseUrl}/api/users/getDoctorsSuggestionInfo/${ids}`);
  }

  updatePatientProfile(patientId : number,data : any){
    const formData: FormData = new FormData();
    formData.append('firstName',data.firstName != null?data.firstName:'')
    formData.append('lastName',data.lastName != null?data.lastName:'')
    formData.append('height',data.height != null?data.height:'')
    formData.append('weight',data.weight != null?data.weight:'')
    formData.append('bloodType',data.bloodType != null?data.bloodType:'')
    formData.append('phone',data.phone != null ?data.phone:'')
    formData.append('birthDate',data.birthDate != null ? moment(data.birthDate).format('YYYY-MM-DD'):'')
    formData.append('profileImage',data.profileImage != null ?data.profileImage:'')

   return this.http.put<any>(`${this.baseUrl}/api/users/updatePatientProfile/${patientId}`,formData);
  }

  getSearchedDoctors(data : any){
    return this.http.post<DoctorSearch[]>(`${this.baseUrl}/api/users/getSearchedDoctors`,data);
  }
  
}
