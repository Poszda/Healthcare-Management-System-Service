import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';

@Injectable()
export class SpecialitiesService {

  private baseUrl = environment.apiUrl;
  constructor(private http: HttpClient) { }

  getSpecialities(){
    return this.http.get<any>(`${this.baseUrl}/api/specialities/getSpecialities`);
  }

  getSpecialitiesWithProcedures(){
    return this.http.get<any>(`${this.baseUrl}/api/specialities/getSpecialitiesWithProcedures`);
  }
}
