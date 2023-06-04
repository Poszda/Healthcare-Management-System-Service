import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { TodayProgramStatistic } from '../models/today-program-statistic.model';
import { PatientsVisitsByAgeGroup } from '../models/patients-visits-by-age-group.model';
import { InterventionsByProcedure } from '../models/doctor-procedures-count.model';
import { AppointmentsCountByStatus } from '../models/appointments-count-by-status.model';

@Injectable()
export class StatisticsService {

  constructor(private http : HttpClient) { }

  getTodayProgramStatistic(doctorId : number){
    return this.http.get<TodayProgramStatistic>(`http://localhost:8080/api/statistics/getTodayProgram/${doctorId}`);
  }

  getPatientsVisitsByAgeGroup(doctorId : number){
    return this.http.get<PatientsVisitsByAgeGroup[]>(`http://localhost:8080/api/statistics/getDoctorPatientsVisitsByAgeGroup/${doctorId}`);
  }
  
  getDoctorInterventionsByProcedure(doctorId : number){
    return this.http.get<InterventionsByProcedure[]>(`http://localhost:8080/api/statistics/getDoctorInterventionsByCountProcedure/${doctorId}`);
  }

  getDoctorAppointmentsCountsByStatus(doctorId : number){
    return this.http.get<AppointmentsCountByStatus[]>(`http://localhost:8080/api/statistics/getDoctorAppointmentsCountsByStatus/${doctorId}`);
  }
}

