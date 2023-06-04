import { Component,OnInit,Input, OnChanges } from '@angular/core';
import { SpecialityFrequencyPercentage } from 'src/app/admin/models/speciality-frequency.model';

@Component({
  selector: 'app-speciality-visits-widget',
  templateUrl: './speciality-visits-widget.component.html',
  styleUrls: ['./speciality-visits-widget.component.css']
})
export class SpecialityVisitsWidgetComponent implements OnChanges{

  @Input() frequencies : SpecialityFrequencyPercentage[] = []
  noVisits : boolean = false;

  /* BASIC OPTIONS */
  options = {
    plugins: {
      tooltip: {
        enabled: true
      },
      legend: {
        labels: {
          color: '#495057'
        },
        position: 'right',
      },
    }
  }

  /* BASIC PATTERN */
  data : any = {
    datasets: [
      {
        data: [1,0,0,0],
        backgroundColor: [
          "#6D71F9",
          "#54C1FB",
          "#272848",
          "grey"
        ],
        hoverBackgroundColor: [
          "#6D71F9",
          "#54C1FB",
          "272848",
          "grey"
        ]
      }
    ]
  };

  ngOnChanges(): void {
    const result = this.frequencies.map(el => el.appointments)
    if(result.length === 0) this.noVisits = true;
    else{
      this.noVisits = false;
      this.data.datasets[0].data = result
      this.refreshChart();
    }
  }

  refreshChart(){
    this.data = JSON.parse(JSON.stringify(this.data))
  }

  
}
