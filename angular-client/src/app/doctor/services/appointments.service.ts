import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { DoctorAppointment } from '../models/doctor-appointments.model';
import { newDiagnostic } from '../models/new-diagnostic.model';

@Injectable()
export class AppointmentsService {

  constructor(private http : HttpClient) { }

  getDoctorAppointments(doctorId : number){
    return this.http.get<DoctorAppointment[]>(`http://localhost:8080/api/appointments/getDoctorAppointments/${doctorId}`);
  }

  createDiagnostic( diagnostic: newDiagnostic){
    return this.http.post(`http://localhost:8080/api/appointments/createDiagnostic`,diagnostic);
  }
}
