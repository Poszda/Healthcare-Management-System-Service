import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable()
export class HospitalsService {

  constructor(private http: HttpClient) { }

  getAllHospitalsCounties(){
    return this.http.get<any>(`http://localhost:8080/api/hospitals/getAllHospitalCounties`);
  }
}
