import { Component, OnInit } from '@angular/core';
import { StatisticsService } from '../../services/statistics.service';
import { HospitalManagementService } from '../../services/hospital-management.service';
import { HospitalOverview } from '../../models/hospital-overview.model';
import { HospitalMonthStatistic } from '../../models/hospital-month-statistic.model';
import { SpecialityFrequency, SpecialityFrequencyPercentage } from '../../models/speciality-frequency.model';

@Component({
  selector: 'app-stats',
  templateUrl: './stats.component.html',
  styleUrls: ['./stats.component.css']
})
export class StatsComponent implements OnInit {

  hospitalId : number = 0;
  hospitalOverview : HospitalOverview | undefined;
  hospitalPeriodicStats : HospitalMonthStatistic[] = [];
  hospitalSpecialityFrequencyPercentage : SpecialityFrequencyPercentage[] = [];

  constructor(private hospitalManagementService : HospitalManagementService ,private statisticsService: StatisticsService) { }

  ngOnInit(): void {
    this.hospitalId = this.hospitalManagementService.getHospitalFromLocalStorage().id;
      this.getHospitalOverview();
      this.getHospitalPeriodicStats();
      this.getHospitalSpecialityFrequency();
  }

  getHospitalOverview(){
    this.statisticsService.getHospitalOverview(this.hospitalId).subscribe(
      res =>{
        this.hospitalOverview = res
      },
      err =>{
        console.log(err)
      }
    )
  }

  getHospitalPeriodicStats(){
    this.statisticsService.getHospitalPeriodicStats(this.hospitalId).subscribe(
      res =>{
        this.hospitalPeriodicStats = res
      },
      err =>{
        console.log(err)
      }
    )
  }

  getHospitalSpecialityFrequency(){
    this.statisticsService.getHospitalSpecialityFrequency(this.hospitalId).subscribe(
      res =>{
        this.hospitalSpecialityFrequencyPercentage = this.calculateSpecialityPercentages(res)
        console.log(this.hospitalSpecialityFrequencyPercentage)
      },
      err =>{
        console.log(err)
      }
    )
  }

  calculateSpecialityPercentages(frequencies : SpecialityFrequency[]) : SpecialityFrequencyPercentage[]{
    const total = frequencies.reduce((accumulator, currentValue) => accumulator + currentValue.appointments,0)
    const percentages = frequencies.map(el => ({...el, percentage : Number((el.appointments/total * 100).toFixed(2)) })).sort((a, b) => b.percentage - a.percentage)
    const result = percentages.slice(0,3);

    if(frequencies.length > 3){
      const othersPercent = percentages.slice(3).reduce((accumulator, currentValue) => accumulator + currentValue.percentage,0)
      const othersAppointments = percentages.slice(3).reduce((accumulator, currentValue) => accumulator + currentValue.appointments,0)
      const others : SpecialityFrequencyPercentage = {
        percentage: othersPercent,
        speciality: 'Others',
        appointments: othersAppointments
      }
      result.push(others)
    }
    
    return result
  }

}
