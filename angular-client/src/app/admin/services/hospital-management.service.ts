import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable()
export class HospitalManagementService {

  constructor(private http : HttpClient) { }

  getSpecialitiesWithProcedures(){
    return this.http.get<any>(`http://localhost:8080/api/specialities/getSpecialitiesWithProcedures`);
  }

  getProceduresIdsByHospitalId(hospitalId : number){
    return this.http.get<any>(`http://localhost:8080/api/procedures/getProceduresIdsByHospitalId/${hospitalId}`);
  }

  saveHospitalProcedures(hospitalId : number, proceduresIds : number[]){
    return this.http.post(`http://localhost:8080/api/hospitals/saveHospitalProcedures/${hospitalId}`,proceduresIds,{ responseType: 'text' });
  }
}
