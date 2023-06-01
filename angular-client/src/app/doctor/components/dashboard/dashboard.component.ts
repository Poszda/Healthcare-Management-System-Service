import { Component, OnInit } from '@angular/core';
import { AppointmentsService } from '../../services/appointments.service';
import { StatisticsService } from '../../services/statistics.service';
import { DoctorService } from '../../services/doctor.service';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {

  doctorId : number = 0;
  constructor(private statisticsService :StatisticsService, private doctorService : DoctorService) { }

  ngOnInit(): void {
    this.doctorId = this.doctorService.getDoctorFromLocalStorage().id;
    this.getTodayProgramStatistic();
  }

  getTodayProgramStatistic(){
    this.statisticsService.getTodayProgramStatistic(this.doctorId).subscribe(
      res =>{
        console.log(res)
      },
      err =>{
        console.log(err)
      }
    )
  }

}
