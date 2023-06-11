import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable()
export class SpecialitiesService {

  constructor(private http: HttpClient) { }

  getSpecialities(){
    return this.http.get<any>(`http://localhost:8080/api/specialities/getSpecialities`);
  }

  getSpecialitiesWithProcedures(){
    return this.http.get<any>(`http://localhost:8080/api/specialities/getSpecialitiesWithProcedures`);
  }
}
