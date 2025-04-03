import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { DoctorAppointment } from '../models/doctor-appointments.model';
import { newDiagnostic } from '../models/new-diagnostic.model';
import { AppointmentNext } from 'src/app/shared/models/appointment-next.model';
import { environment } from 'src/environments/environment';

@Injectable()
export class AppointmentsService {

  private baseUrl = environment.apiUrl;

  constructor(private http : HttpClient) { }

  getDoctorAppointments(doctorId : number){
    return this.http.get<DoctorAppointment[]>(`${this.baseUrl}/api/appointments/getDoctorAppointments/${doctorId}`);
  }

  createDiagnostic( diagnostic: newDiagnostic){
    return this.http.post(`${this.baseUrl}/api/appointments/createDiagnostic`,diagnostic);
  }

  getTodayNextAppointments(doctorId : number){
    return this.http.get<AppointmentNext[]>(`${this.baseUrl}/api/appointments/getDoctorTodayNextAppointments/${doctorId}`);
  }
}
