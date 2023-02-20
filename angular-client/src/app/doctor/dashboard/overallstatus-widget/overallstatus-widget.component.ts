import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-overallstatus-widget',
  templateUrl: './overallstatus-widget.component.html',
  styleUrls: ['./overallstatus-widget.component.css']
})
export class OverallstatusWidgetComponent implements OnInit {
  lineStylesData: any;
  basicOptions : any;

  constructor() { 
    this.lineStylesData = {
      labels: ['January', 'February', 'March', 'April', 'May', 'June', 'July'],
      datasets: [
        {
          label: 'First Dataset',
          data: [0, 0, 0, 0, 0, 0, 0],
          fill: true,
          tension: .4,
          borderColor: '#6D71F9',
          backgroundColor: '#6D71F955'
          
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
  }

  ngOnInit(): void {
    setTimeout(() => {
      this.lineStylesData = {
        labels: ['January', 'February', 'March', 'April', 'May', 'June', 'July'],
        datasets: [
          {
            label: 'First Dataset',
            data: [11, 59, 80, 81, 56, 55, 40],
            fill: true,
            tension: .4,
            borderColor: '#6D71F9',
            backgroundColor: '#6D71F955'
            
          },
          {
            label: 'Second Dataset',
            data: [28, 48, 40, 19, 86, 27, 90],
            fill: true,
            tension: .4,
            borderColor: '#54C1FB',
            backgroundColor: '#54c1fb55'
          },
          {
            label: 'Third Dataset',
            data: [12, 51, 62, 33, 21, 62, 45],
            fill: true,
            borderColor: '#272848',
            tension: .4,
            backgroundColor: '#27284855'
          }
        ]
      };
    }, 4000);

    this.basicOptions = {
      plugins: {
        legend: {
          labels: {
            color: '#495057'
          }
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
      }
    };
  }

}
