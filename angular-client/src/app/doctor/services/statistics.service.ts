import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { TodayProgramStatistic } from '../models/today-program-statistic.model';
import { PatientsVisitsByAgeGroup } from '../models/patients-visits-by-age-group.model';
import { InterventionsByProcedure } from '../models/doctor-procedures-count.model';
import { AppointmentsCountByStatus } from '../models/appointments-count-by-status.model';
import { environment } from 'src/environments/environment';

@Injectable()
export class StatisticsService {

  private baseUrl = environment.apiUrl;

  constructor(private http : HttpClient) { }

  getTodayProgramStatistic(doctorId : number){
    return this.http.get<TodayProgramStatistic>(`${this.baseUrl}/api/statistics/getTodayProgram/${doctorId}`);
  }

  getPatientsVisitsByAgeGroup(doctorId : number){
    return this.http.get<PatientsVisitsByAgeGroup[]>(`${this.baseUrl}/api/statistics/getDoctorPatientsVisitsByAgeGroup/${doctorId}`);
  }
  
  getDoctorInterventionsByProcedure(doctorId : number){
    return this.http.get<InterventionsByProcedure[]>(`${this.baseUrl}/api/statistics/getDoctorInterventionsByCountProcedure/${doctorId}`);
  }

  getDoctorAppointmentsCountsByStatus(doctorId : number){
    return this.http.get<AppointmentsCountByStatus[]>(`${this.baseUrl}/api/statistics/getDoctorAppointmentsCountsByStatus/${doctorId}`);
  }
}

