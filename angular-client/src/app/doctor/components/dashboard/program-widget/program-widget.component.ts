import { Component, OnInit,Input, OnChanges, SimpleChanges } from '@angular/core';
import DatalabelsPlugin from 'chartjs-plugin-datalabels';
import { TodayProgramStatistic } from 'src/app/doctor/models/today-program-statistic.model';

@Component({
  selector: 'app-program-widget',
  templateUrl: './program-widget.component.html',
  styleUrls: ['./program-widget.component.css']
})
export class ProgramWidgetComponent implements OnInit, OnChanges {

  @Input() program : TodayProgramStatistic | undefined

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
          return percentage !== 0 ? percentage.toFixed(0) + '%' : '';
        },
        color:'white',
      },
    }
  }

  

  pieChartPlugins = [ DatalabelsPlugin ];
  chartData : any = {
    /* labels: ['Free', 'Busy'], */
    datasets: [
      {
        data: [100, 0],
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

  constructor() {
  }

  ngOnChanges(changes: SimpleChanges): void {
    this.updateChart();
  }

  ngOnInit(): void {
  }

  updateChart(){
    if(this.program){
      this.chartData.datasets[0].data = [this.program.free,this.program.busy]
      this.refreshChart();
    }
  }

  refreshChart(){
    this.chartData = JSON.parse(JSON.stringify(this.chartData))
  }

}
