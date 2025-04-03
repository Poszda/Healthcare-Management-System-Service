import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';

@Injectable()
export class HospitalsService {

  private baseUrl = environment.apiUrl;
  constructor(private http: HttpClient) { }

  getAllHospitalsCounties(){
    return this.http.get<any>(`${this.baseUrl}/api/hospitals/getAllHospitalCounties`);
  }
}
