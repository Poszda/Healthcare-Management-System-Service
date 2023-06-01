import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { TodayProgramStatistic } from '../models/TodayProgramStatistic.model';

@Injectable()
export class StatisticsService {

  constructor(private http : HttpClient) { }

  getTodayProgramStatistic(doctorId : number){
    return this.http.get<TodayProgramStatistic>(`http://localhost:8080/api/statistics/getTodayProgram/${doctorId}`);
  }
}

