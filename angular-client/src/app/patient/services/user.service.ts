import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable()
export class UserService {

  constructor(private http: HttpClient) { }

  getDoctorsById(ids : number[]){
    return this.http.get<any>(`http://localhost:8080/api/users/getDoctorsById/${ids}`);
  }
  getDoctorsWithHospitalsById(ids : number[]){
    return this.http.get<any>(`http://localhost:8080/api/users/getDoctorsWithUsersAndHospitalsById/${ids}`);
  }
}
