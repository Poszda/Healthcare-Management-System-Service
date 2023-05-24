import { Component,OnInit,Input, OnChanges } from '@angular/core';
import { SpecialityFrequencyPercentage } from 'src/app/admin/models/speciality-frequency.model';

@Component({
  selector: 'app-speciality-visits-widget',
  templateUrl: './speciality-visits-widget.component.html',
  styleUrls: ['./speciality-visits-widget.component.css']
})
export class SpecialityVisitsWidgetComponent implements OnChanges{

  @Input() frequencies : SpecialityFrequencyPercentage[] = []

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
    // labels: ['Free', 'Busy'],
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
    this.data.datasets[0].data = this.frequencies.map(el => el.appointments)
    this.data = JSON.parse(JSON.stringify(this.data))
  }

  
}
