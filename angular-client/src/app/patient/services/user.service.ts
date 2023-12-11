import { formatDate } from '@angular/common';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import * as moment from 'moment';
import { Patient } from 'src/app/core/models/patient.model';
import { DoctorSearch } from '../models/doctor-search.model';

@Injectable()
export class UserService {

  constructor(private http: HttpClient) { }

  getPatientIdFromLocalStorage() : number | null{
    return localStorage.getItem('patient')? Number(JSON.parse(localStorage.getItem('patient')!).id) : null;
  }

  getUserFromLocalStorage() : any{
    return localStorage.getItem('patient')? JSON.parse(localStorage.getItem('user')!) : null;
  }

  getPatient(patientId : number){
    return this.http.get<Patient>(`http://localhost:8080/api/users/getPatient/${patientId}`);
  }

  getDoctorsById(ids : number[]){
    return this.http.get<any>(`http://localhost:8080/api/users/getDoctorsById/${ids}`);
  }

  getDoctorsWithHospitalsById(ids : number[]){
    return this.http.get<any>(`http://localhost:8080/api/users/getDoctorsWithUsersAndHospitalsById/${ids}`);
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

   return this.http.put<any>(`http://localhost:8080/api/users/updatePatientProfile/${patientId}`,formData);
  }

  getSearchedDoctors(data : any){
    return this.http.post<DoctorSearch[]>(`http://localhost:8080/api/users/getSearchedDoctors`,data);
  }
  
}
