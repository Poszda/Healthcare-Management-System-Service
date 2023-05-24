import { Component, Input, OnInit,OnChanges, SimpleChanges } from '@angular/core';
import { HospitalMonthStatistic } from 'src/app/admin/models/hospital-month-statistic.model';

@Component({
  selector: 'app-period-overview-widget',
  templateUrl: './period-overview-widget.component.html',
  styleUrls: ['./period-overview-widget.component.css']
})
export class PeriodOverviewWidgetComponent implements OnInit,OnChanges {
  @Input() stats: HospitalMonthStatistic[] = [];

  /* BASIC OPTIONS */
  basicOptions = {
    responsive: true, // seems that is not needed
    maintainAspectRatio: false, // seems that is not needed even if should
    plugins: {
      legend: {
        labels: {
          color: '#495057'
        }
      },

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

  /* BASIC PATTERN */
  lineStylesData : any= {
    labels: [], //'January', 'February', 'March', 'April', 'May', 'June'
    datasets: [
      {
        label: 'Appointments',
        data: [], //0, 2, 0, 20, 0, 10
        fill: true,
        tension: .4,
        borderColor: '#6D71F9',
        backgroundColor: '#6D71F955',
        pointHitRadius: 15,
        pointHoverRadius: 8,

      },
      {
        label: 'Earnings',
        data: [], //0, 0, 0, 20, 0, 0
        fill: true,
        tension: .4,
        borderColor: '#54C1FB',
        backgroundColor: '#54c1fb55',
        pointHitRadius: 15,
        pointHoverRadius: 8,
      }
    ]
  };

  constructor() {
  }

  ngOnChanges(changes : SimpleChanges): void {
    this.lineStylesData.labels = this.stats.map(el => el.month) 
    this.lineStylesData.datasets[0].data = this.stats.map(el => el.appointments) 
    this.lineStylesData.datasets[1].data = this.stats.map(el => el.total) 
    this.lineStylesData = JSON.parse(JSON.stringify(this.lineStylesData))
  }

  ngOnInit(): void {
  }

  extractLabels(){
    //this.stats.map(el => el.month)
  }


}
