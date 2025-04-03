import { Injectable, } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { HospitalOverview } from '../models/hospital-overview.model';
import { SpecialityFrequency } from '../models/speciality-frequency.model';
import { HospitalMonthStatistic } from '../models/hospital-month-statistic.model';
import { environment } from 'src/environments/environment';

@Injectable()
export class StatisticsService {

  private baseUrl = environment.apiUrl;

  constructor(private http : HttpClient) { }

  getHospitalOverview(hospitalId : number){
    return this.http.get<HospitalOverview>(`${this.baseUrl}/api/statistics/getHospitalOverview/${hospitalId}`);
  }
  getHospitalPeriodicStats(hospitalId : number){
    return this.http.get<HospitalMonthStatistic[]>(`${this.baseUrl}/api/statistics/getHospitalPeriodicStats/${hospitalId}`);
  }
  getHospitalSpecialityFrequency(hospitalId : number) {
    return this.http.get<SpecialityFrequency[]>(`${this.baseUrl}/api/statistics/getHospitalSpecialityFrequency/${hospitalId}`);
  }

}
