import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { newDoctor } from '../models/new-doctor.model';

@Injectable()
export class HospitalManagementService {

  constructor(private http : HttpClient) { }

  getHospitalSpecialities(hospitalId : number){
    return this.http.get<any>(`http://localhost:8080/api/specialities/getHospitalSpecialities/${hospitalId}`);
  }

  getSpecialitiesWithProcedures(){
    return this.http.get<any>(`http://localhost:8080/api/specialities/getSpecialitiesWithProcedures`);
  }

  getProceduresIdsByHospitalId(hospitalId : number){
    return this.http.get<any>(`http://localhost:8080/api/procedures/getProceduresIdsByHospitalId/${hospitalId}`);
  }

  saveHospitalProcedures(hospitalId : number, proceduresIds : number[]){
    return this.http.post(`http://localhost:8080/api/hospitals/saveHospitalProcedures/${hospitalId}`,proceduresIds,{ responseType: 'text' });
  }
  
  createDoctor(doctor : newDoctor){
    return this.http.post(`http://localhost:8080/api/users/createDoctor`,doctor);
  }

  getHospitalFromLocalStorage(){
    const item = localStorage.getItem('admin')
    if(item) return JSON.parse(item)?.hospital;
    return null;
  }
}
