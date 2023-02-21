import { Component, OnInit } from '@angular/core';
import DatalabelsPlugin from 'chartjs-plugin-datalabels';

@Component({
  selector: 'app-program-widget',
  templateUrl: './program-widget.component.html',
  styleUrls: ['./program-widget.component.css']
})
export class ProgramWidgetComponent implements OnInit {
  /* options */
  chartOptions = {
    plugins: {
      tooltip: {
        enabled: false
      },
      legend: {
        labels: {
          color: '#495057'
        },
        position: 'right',
      },
      datalabels: {
        formatter: (value : any, ctx : any) => {
          let sum = 0;
          const dataArr = ctx.chart.data.datasets[0].data;
          dataArr.map((data : any) => {
                sum += data;
          });
          const percentage = (value * 100 / sum); 
          return percentage !== 0 ? percentage.toFixed(2) + '%' : '';
        },
        color:'white',
      },
    }
  }
  public pieChartPlugins = [ DatalabelsPlugin ];
  chatData: any;

  constructor() {
  }

  ngOnInit(): void {
    this.chatData = {
      /* labels: ['Free', 'Busy'], */
      datasets: [
        {
          data: [30, 50],
          backgroundColor: [
            "#6D71F9",
            "#54C1FB"
          ],
          hoverBackgroundColor: [
            "#6D71F9",
            "#54C1FB",
          ]
        }
      ]
    };
  }

}
