import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { DoctorsAvailableHours } from '../models/doctor-available-hours.model';
import { NewAppointment } from '../models/new-appointment.model';
import { AppointmentCard } from '../models/appointment-card.model';
import { AppointmentNext } from 'src/app/shared/models/appointment-next.model';
import { Subject } from 'rxjs';
import { environment } from 'src/environments/environment';

@Injectable()
export class AppointmentsService {

  private baseUrl = environment.apiUrl;
  resetButtonsColorExcept : Subject<any> = new Subject<any>();
  
  constructor(private http: HttpClient) { }

  getAppointmentOptionals(county : string, procedureId : number){
    return this.http.get<any>(`${this.baseUrl}/api/appointments/getAppointmentOptionals/${county}/${procedureId}`);
  }

  getDoctorsAvailableHours(req : any){
    return this.http.get<DoctorsAvailableHours[]>(`${this.baseUrl}/api/appointments/getAvailableAppointments/${req.doctorsIds}/${req.procedureId}/${req.startDate}/${req.endDate}`);
  }

  createAppointment(appointment : NewAppointment){
    return this.http.post<NewAppointment>(`${this.baseUrl}/api/appointments/createAppointment`, appointment);
  }

  getAppointmentsCards(patientId : number){
    return this.http.get<AppointmentCard[]>(`${this.baseUrl}/api/appointments/getAppointmentsCards/${patientId}`);
  }
  
  deleteAppointment(id : number){
    return this.http.delete(`${this.baseUrl}/api/appointments/deleteAppointment/${id}`,{responseType: 'text'});
  }

  getDiagnostics(patientId : number){
    return this.http.get<any>(`${this.baseUrl}/api/appointments/getPatientDiagnostics/${patientId}`);
  }

  getNextAppointments(patientId : number){
    return this.http.get<AppointmentNext[]>(`${this.baseUrl}/api/appointments/getPatientNextAppointments/${patientId}`);
  }
}
