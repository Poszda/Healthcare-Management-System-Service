import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";

@Injectable({
    providedIn: 'root'
})
export class UserGlobalService {
    constructor(private http: HttpClient){}

    getDoctorProfile(doctorId : number) {
        return this.http.get<any>(`http://localhost:8080/api/users/getDoctorProfile/${doctorId}`);
    }
    
    getPatientProfile(patientId : number) {
        return this.http.get<any>(`http://localhost:8080/api/users/getPatientProfile/${patientId}`);
    }

}