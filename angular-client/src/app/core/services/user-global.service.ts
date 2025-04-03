import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { environment } from "src/environments/environment";

@Injectable({
    providedIn: 'root'
})
export class UserGlobalService {
    private baseUrl = environment.apiUrl;
    constructor(private http: HttpClient){}

    getDoctorProfile(doctorId : number) {
        return this.http.get<any>(`${this.baseUrl}/api/users/getDoctorProfile/${doctorId}`);
    }
    
    getPatientProfile(patientId : number) {
        return this.http.get<any>(`${this.baseUrl}/api/users/getPatientProfile/${patientId}`);
    }

}