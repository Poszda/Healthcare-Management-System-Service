import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Patient } from 'src/app/core/models/patient.model';

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
    return this.http.put<any>(`http://localhost:8080/api/users/updatePatientProfile/${patientId}`,data);
  }

  getSearchedDoctors(data : any){
    return this.http.post<any>(`http://localhost:8080/api/users/getSearchedDoctors`,data);
  }
  
}
