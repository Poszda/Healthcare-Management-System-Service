import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable()
export class UserService {

  constructor(private http: HttpClient) { }

  getPatientIdFromLocalStorage() : number | null{
    return localStorage.getItem('patient')? Number(JSON.parse(localStorage.getItem('patient')!).id) : null;
  }

  getDoctorsById(ids : number[]){
    return this.http.get<any>(`http://localhost:8080/api/users/getDoctorsById/${ids}`);
  }
  getDoctorsWithHospitalsById(ids : number[]){
    return this.http.get<any>(`http://localhost:8080/api/users/getDoctorsWithUsersAndHospitalsById/${ids}`);
  }
}
