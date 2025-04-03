import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { DoctorEditableData } from '../models/doctor-editable-data.model';
import { environment } from 'src/environments/environment';

@Injectable()
export class DoctorService {

  private baseUrl = environment.apiUrl;

  constructor(private http : HttpClient) { }

  updateDoctorProfile(doctorId : number,data : DoctorEditableData){    
    const formData: FormData = new FormData();
    formData.append('profileImage',data.profileImage)
    formData.append('university',data.university)
    formData.append('description',data.description)
    return this.http.put<any>(`${this.baseUrl}/api/users/updateDoctorProfile/${doctorId}`,formData);
  }

  getDoctorFromLocalStorage(){
    const item = localStorage.getItem('doctor')
    if(item) return JSON.parse(item);
    return null;
  }

}
