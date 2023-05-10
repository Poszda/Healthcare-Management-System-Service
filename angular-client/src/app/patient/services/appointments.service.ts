import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { DoctorsAvailableHours } from '../models/doctor-available-hours.model';

@Injectable()
export class AppointmentsService {

  constructor(private http: HttpClient) { }

  getFormOptionalOptions(county : string, procedureId : number){
    return this.http.get<any>(`http://localhost:8080/api/appointments/getHospitalsAndDoctorsRecommendations/${county}/${procedureId}`);
  }

  getDoctorsAvailableHours(req : any){
    return this.http.get<DoctorsAvailableHours[]>(`http://localhost:8080/api/appointments/getAvailableAppointments/${req.doctorsIds}/${req.procedureId}/${req.startDate}/${req.endDate}`);
  }

}
