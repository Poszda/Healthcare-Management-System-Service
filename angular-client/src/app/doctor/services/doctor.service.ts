import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable()
export class DoctorService {

  constructor(private http : HttpClient) { }

  updateDoctorUniversityAndDescription(doctorId : number,data : any){
    return this.http.put<any>(`http://localhost:8080/api/users/updateDoctorUniversityAndDescription/${doctorId}`,data);
  }

  getDoctorFromLocalStorage(){
    const item = localStorage.getItem('doctor')
    if(item) return JSON.parse(item);
    return null;
  }

}
