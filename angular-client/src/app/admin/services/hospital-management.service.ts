import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { newDoctor } from '../models/new-doctor.model';
import { environment } from 'src/environments/environment';

@Injectable()
export class HospitalManagementService {

  private baseUrl = environment.apiUrl;

  constructor(private http : HttpClient) { }

  getHospitalSpecialities(hospitalId : number){
    return this.http.get<any>(`${this.baseUrl}/api/specialities/getHospitalSpecialities/${hospitalId}`);
  }

  getSpecialitiesWithProcedures(){
    return this.http.get<any>(`${this.baseUrl}/api/specialities/getSpecialitiesWithProcedures`);
  }

  getProceduresIdsByHospitalId(hospitalId : number){
    return this.http.get<any>(`${this.baseUrl}/api/procedures/getProceduresIdsByHospitalId/${hospitalId}`);
  }

  getDoctorsTableData(hospitalId : number){
    return this.http.get<any>(`${this.baseUrl}/api/users/getHospitalDoctorsWithSpeciality/${hospitalId}`);
  }

  saveHospitalProcedures(hospitalId : number, proceduresIds : number[]){
    return this.http.post(`${this.baseUrl}/api/hospitals/saveHospitalProcedures/${hospitalId}`,proceduresIds,{ responseType: 'text' });
  }
  
  createDoctor(doctor : newDoctor){
    return this.http.post(`${this.baseUrl}/api/users/createDoctor`,doctor);
  }

  deleteDoctor(doctorId : number){
    return this.http.delete(`${this.baseUrl}/api/users/deleteDoctor/${doctorId}`);
  }

  getHospitalFromLocalStorage(){
    const item = localStorage.getItem('admin')
    if(item) return JSON.parse(item)?.hospital;
    return null;
  }
}
