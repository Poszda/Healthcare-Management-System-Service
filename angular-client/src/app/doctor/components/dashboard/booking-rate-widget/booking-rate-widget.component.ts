import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-booking-rate-widget',
  templateUrl: './booking-rate-widget.component.html',
  styleUrls: ['./booking-rate-widget.component.css']
})
export class BookingRateWidgetComponent implements OnInit {
  stackedData: any;
  stackedOptions: any;
  constructor(){}

  ngOnInit(): void {
    this.stackedData = {
      labels: ['January', 'February', 'March', 'April', 'May', 'June', 'July'],
      datasets: [{
          type: 'bar',
          label: 'Dataset 1',
          backgroundColor: '#6D71F9',
          data: [
              50,
              25,
              12,
              48,
              90,
              76,
              42
          ]
      }, {
          type: 'bar',
          label: 'Dataset 2',
          backgroundColor: '#54C1FB',
          data: [
              21,
              84,
              24,
              75,
              37,
              65,
              34
          ]
      }, {
          type: 'bar',
          label: 'Dataset 3',
          backgroundColor: '#272848',
          data: [
              41,
              52,
              24,
              74,
              23,
              21,
              32
          ]
      }]
  };

  setTimeout(() => {
    this.stackedData = {
      labels: ['January', 'February', 'March', 'April', 'May', 'June', 'July'],
      datasets: [{
          type: 'bar',
          label: 'Dataset 1',
          backgroundColor: '#6D71F9',
          data: [
              50,
              25,
              500,
              48,
              90,
              100,
              42
          ]
      }, {
          type: 'bar',
          label: 'Dataset 2',
          backgroundColor: '#54C1FB',
          data: [
              21,
              814,
              24,
              715,
              37,
              65,
              34
          ]
      }, {
          type: 'bar',
          label: 'Dataset 3',
          backgroundColor: '#272848',
          data: [
              41,
              52,
              24,
              74,
              23,
              21,
              32
          ]
      }]
  };
  }, 3000);

  this.stackedOptions = {
      tooltips: {
          mode: 'index',
          intersect: false
      },
      responsive: true,
      scales: {
          x: {
              stacked: true,
              ticks: {
                min: 0,
                max: 500
              }
          },
          y: {
              stacked: true
          }
      }
  };
  }

}
