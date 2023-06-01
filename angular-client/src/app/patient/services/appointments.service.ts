import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { DoctorsAvailableHours } from '../models/doctor-available-hours.model';
import { NewAppointment } from '../models/new-appointment.model';
import { AppointmentCard } from '../models/appointment-card.model';

@Injectable()
export class AppointmentsService {

  constructor(private http: HttpClient) { }

  getFormOptionalOptions(county : string, procedureId : number){
    return this.http.get<any>(`http://localhost:8080/api/appointments/getHospitalsAndDoctorsRecommendations/${county}/${procedureId}`);
  }

  getDoctorsAvailableHours(req : any){
    return this.http.get<DoctorsAvailableHours[]>(`http://localhost:8080/api/appointments/getAvailableAppointments/${req.doctorsIds}/${req.procedureId}/${req.startDate}/${req.endDate}`);
  }

  createAppointment(appointment : NewAppointment){
    return this.http.post<NewAppointment>("http://localhost:8080/api/appointments/createAppointment", appointment);
  }

  getAppointmentsCards(patientId : number){
    return this.http.get<AppointmentCard[]>(`http://localhost:8080/api/appointments/getAppointmentsCards/${patientId}`);
  }
  
  deleteAppointment(id : number){
    return this.http.delete(`http://localhost:8080/api/appointments/deleteAppointment/${id}`,{responseType: 'text'});
  }

  getDiagnostics(patientId : number){
    return this.http.get<any>(`http://localhost:8080/api/appointments/getPatientDiagnostics/${patientId}`);
  }
}
