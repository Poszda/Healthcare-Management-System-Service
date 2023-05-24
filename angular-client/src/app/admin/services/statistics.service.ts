import { Injectable, } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { HospitalOverview } from '../models/hospital-overview.model';
import { SpecialityFrequency } from '../models/speciality-frequency.model';
import { HospitalMonthStatistic } from '../models/hospital-month-statistic.model';

@Injectable()
export class StatisticsService {

  constructor(private http : HttpClient) { }

  getHospitalOverview(hospitalId : number){
    return this.http.get<HospitalOverview>(`http://localhost:8080/api/statistics/getHospitalOverview/${hospitalId}`);
  }
  getHospitalPeriodicStats(hospitalId : number){
    return this.http.get<HospitalMonthStatistic[]>(`http://localhost:8080/api/statistics/getHospitalPeriodicStats/${hospitalId}`);
  }
  getHospitalSpecialityFrequency(hospitalId : number) {
    return this.http.get<SpecialityFrequency[]>(`http://localhost:8080/api/statistics/getHospitalSpecialityFrequency/${hospitalId}`);
  }

}
