import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { DoctorEditableData } from '../models/doctor-editable-data.model';

@Injectable()
export class DoctorService {

  constructor(private http : HttpClient) { }

  updateDoctorProfile(doctorId : number,data : DoctorEditableData){    
    const formData: FormData = new FormData();
    formData.append('profileImage',data.profileImage)
    formData.append('university',data.university)
    formData.append('description',data.description)
    return this.http.put<any>(`http://localhost:8080/api/users/updateDoctorProfile/${doctorId}`,formData);
  }

  getDoctorFromLocalStorage(){
    const item = localStorage.getItem('doctor')
    if(item) return JSON.parse(item);
    return null;
  }

}
