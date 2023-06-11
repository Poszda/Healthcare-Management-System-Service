import { Component, OnInit } from '@angular/core';
import { StatisticsService } from '../../services/statistics.service';
import { DoctorService } from '../../services/doctor.service';
import { TodayProgramStatistic } from '../../models/today-program-statistic.model';
import { PatientsVisitsByAgeGroup } from '../../models/patients-visits-by-age-group.model';
import { InterventionsByProcedure } from '../../models/doctor-procedures-count.model';
import { ProcedureTrends } from '../../models/procedure-trends.model';
import { AppointmentsCountByStatus } from '../../models/appointments-count-by-status.model';
import { OverallStatusAgeStatistic } from '../../models/overall-status-age-statistic.model';
import { AppointmentsService } from '../../services/appointments.service';
import { AppointmentNext } from 'src/app/shared/models/appointment-next.model';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {

  todayProgramStatistic: TodayProgramStatistic | undefined
  todayNextAppointments : AppointmentNext[] = []
  patientsVisitsStatistic: OverallStatusAgeStatistic | undefined
  appointmentsCountsByStatus : AppointmentsCountByStatus[] = []
  procedureTrends: ProcedureTrends | undefined
  doctorId: number = 0;
  constructor(private statisticsService: StatisticsService, private doctorService: DoctorService, private appointmentsService : AppointmentsService) { }

  ngOnInit(): void {
    this.doctorId = this.doctorService.getDoctorFromLocalStorage().id;
    this.getTodayProgramStatistic();
    this.getTodayNextAppointments();
    this.getPatientsVisitsByAgeGroup();
    this.getDoctorAppointmentsCountsByStatus();
    this.getDoctorInterventionsByProcedure();
    // setInterval(()=>{
    //   this.getTodayProgramStatistic();
    //   this.getPatientsVisitsByAgeGroup();
    //   this.getDoctorAppointmentsCountsByStatus();
    //   this.getDoctorInterventionsByProcedure();
    // },5000)
  }

  getTodayNextAppointments(){
    this.appointmentsService.getTodayNextAppointments(this.doctorId).subscribe(
      res => {
        this.todayNextAppointments = res.slice(0,10)
      },
      err => {
        console.log(err)
      }
    )
  }

  getTodayProgramStatistic() {
    this.statisticsService.getTodayProgramStatistic(this.doctorId).subscribe(
      res => {
        this.todayProgramStatistic = res;
      },
      err => {
        console.log(err)
      }
    )
  }

  getPatientsVisitsByAgeGroup() {
    this.statisticsService.getPatientsVisitsByAgeGroup(this.doctorId).subscribe(
      res => {
        console.log(res)
        this.patientsVisitsStatistic = this.transformToAgeStatistic(res);
      },
      err => {
        console.log(err)
      }
    )
  }

  transformToAgeStatistic(data : PatientsVisitsByAgeGroup[]){
    const months = data.map(el => el.month);
    const ageGroups = ["0 - 25 yrs", "25 - 65 yrs","65+ yrs"]
    const youngPatients = data.map(el => el.youngPatients)
    const adultPatients = data.map(el => el.adultPatients)
    const oldPatients = data.map(el => el.oldPatients)
    const result : OverallStatusAgeStatistic = {
      months: months,
      ageGroups : ageGroups,
      youngPatients: youngPatients,
      adultPatients: adultPatients,
      oldPatients: oldPatients
    }
    return result;
  }

  getDoctorAppointmentsCountsByStatus() {
    this.statisticsService.getDoctorAppointmentsCountsByStatus(this.doctorId).subscribe(
      res => {
        this.appointmentsCountsByStatus = res
      },
      err => {
        console.log(err)
      }
    )
  }

  getDoctorInterventionsByProcedure() {
    this.statisticsService.getDoctorInterventionsByProcedure(this.doctorId).subscribe(
      res => {
        console.log(res)
        this.procedureTrends = this.obtainTopThreeInterventions(res);
      },
      err => {
        console.log(err)
      }
    )
  }

  

  obtainTopThreeInterventions(data: InterventionsByProcedure[]) {
    const months = data.map(el => el.month);
    const topThree = this.obtainTop3ProcedureNames(data);

    const topThreeProcedures: { name: string, monthly: number[] }[] = topThree.map(el => ({ name: el, monthly: Array(months.length).fill(0) }))
    
    topThreeProcedures.forEach(element =>{
      data.forEach((month,index) => {
        month.procedures.forEach(p => {
          if(p.name === element.name) element.monthly[index] = p.total;
        })
      })
    })

    const result : ProcedureTrends = {
      months: months,
      procedures: topThreeProcedures
    } 

    return result;
  }


  obtainTop3ProcedureNames(data: InterventionsByProcedure[]){
    const map = new Map();
    data.forEach(month => {
      month.procedures.forEach(p => {
        if (map.has(p.name)) map.set(p.name, map.get(p.name) + p.total)
        else map.set(p.name, p.total)
      })
    });
    const mapEntries = Array.from(map);
    const sortedEntries = mapEntries.sort((a, b) => b[1] - a[1]);
    const topThree = sortedEntries.slice(0, 3).map(entry => entry[0]);
    return topThree
  }

}
