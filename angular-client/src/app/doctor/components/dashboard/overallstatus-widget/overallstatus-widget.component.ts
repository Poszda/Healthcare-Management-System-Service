import { Component, OnInit,Input,OnChanges, SimpleChanges } from '@angular/core';
import { AppointmentStatus } from 'src/app/doctor/models/appointment-status.enum';
import { AppointmentsCountByStatus } from 'src/app/doctor/models/appointments-count-by-status.model';
import { OverallStatusAgeStatistic } from 'src/app/doctor/models/overall-status-age-statistic.model';

@Component({
  selector: 'app-overallstatus-widget',
  templateUrl: './overallstatus-widget.component.html',
  styleUrls: ['./overallstatus-widget.component.css']
})
export class OverallstatusWidgetComponent implements OnInit,OnChanges {

  @Input() patientsStatistic : OverallStatusAgeStatistic | undefined
  @Input() appointmentsStatus : AppointmentsCountByStatus[] = []
  upcoming : number = 0
  inProgress : number = 0
  reviewed : number = 0;


  options = {
    responsive : true,
    maintainAspectRatio: false,
    plugins: {
      legend: {
        labels: {
          color: '#495057'
        }
      },
      //tooltip related
      tooltip: {

        backgroundColor: 'white',
        displayColors: true, // removes unnecessary legend
        padding: 10,

        titleColor: 'black',
        titleFont: {
        },

        bodyColor: 'black',
        bodyFont: {
        },
        //borderWidth: 1,
        //borderColor: 'grey',
      }
    },
    scales: {
      x: {
        ticks: {
          color: '#495057'
        },
        grid: {
          color: '#ebedef'
        }
      },
      y: {
        ticks: {
          color: '#495057'
        },
        grid: {
          color: '#ebedef'
        }
      }
    },
  };
  
  data = {
    labels: ['January', 'February', 'March', 'April', 'May', 'June', 'July'],
    datasets: [
      {
        label: 'Pacient Visits',
        data: [0, 0, 0, 0, 0, 0, 0],
        fill: true,
        tension: .4,
        borderColor: '#6D71F9',
        backgroundColor: '#6D71F955',
        pointHitRadius: 15,
        pointHoverRadius: 8,

      },
      {
        label: 'Second Dataset',
        data: [0, 0, 0, 0, 0, 0, 0],
        fill: true,
        tension: .4,
        borderColor: '#54C1FB',
        backgroundColor: '#54c1fb55'
      },
      {
        label: 'Third Dataset',
        data: [0, 0, 0, 0, 0, 0, 0],
        fill: true,
        borderColor: '#272848',
        tension: .4,
        backgroundColor: '#27284855'
      }
    ]
  };
  

  constructor() {
  }

  ngOnChanges(changes : SimpleChanges){
    if(changes['patientsStatistic']){
      this.updateChart()
    }
    if(changes['appointmentsStatus']){
      this.appointmentsStatus.forEach(el => {
        if(el.status === AppointmentStatus.UPCOMING) this.upcoming = el.counter
        if(el.status === AppointmentStatus.IN_PROGRESS) this.inProgress = el.counter
        if(el.status === AppointmentStatus.REVIEWED) this.reviewed = el.counter
      });
    }
  }

  ngOnInit(): void {
      // this.lineStylesData = {
      //   labels: ['January', 'February', 'March', 'April', 'May', 'June', 'July'],
      //   datasets: [
      //     {
      //       label: 'First Dataset',
      //       data: [11, 59, 80, 81, 56, 55, 40],
      //       fill: true,
      //       tension: .4,
      //       borderColor: '#6D71F9',
      //       backgroundColor: '#6D71F955',

      //       //point related
      //       pointHitRadius: 15,
      //     },
      //     {
      //       label: 'Second Dataset',
      //       data: [28, 48, 40, 19, 86, 27, 90],
      //       fill: true,
      //       tension: .4,
      //       borderColor: '#54C1FB',
      //       backgroundColor: '#54c1fb55'
      //     },
      //     {
      //       label: 'Third Dataset',
      //       data: [12, 51, 62, 33, 21, 62, 45],
      //       fill: true,
      //       borderColor: '#272848',
      //       tension: .4,
      //       backgroundColor: '#27284855'
      //     }
      //   ]
      // };
  }

  updateChart() {
    if(this.patientsStatistic){
      this.data.labels = this.patientsStatistic.months;
      this.data.datasets[0].label = this.patientsStatistic.ageGroups[0]
      this.data.datasets[1].label = this.patientsStatistic.ageGroups[1]
      this.data.datasets[2].label = this.patientsStatistic.ageGroups[2]
      this.data.datasets[0].data = this.patientsStatistic.youngPatients;
      this.data.datasets[1].data = this.patientsStatistic.adultPatients;
      this.data.datasets[2].data = this.patientsStatistic.oldPatients;
      this.refreshChart();
    }
}

refreshChart(){
    this.data = JSON.parse(JSON.stringify(this.data))
}

}
