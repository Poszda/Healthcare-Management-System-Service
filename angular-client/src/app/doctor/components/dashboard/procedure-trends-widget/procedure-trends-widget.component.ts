import { Component, OnInit, Input, OnChanges, SimpleChanges } from '@angular/core';
import { InterventionsByProcedure } from 'src/app/doctor/models/doctor-procedures-count.model';
import { ProcedureTrends } from 'src/app/doctor/models/procedure-trends.model';

@Component({
    selector: 'app-procedure-trends-widget',
    templateUrl: './procedure-trends-widget.component.html',
    styleUrls: ['./procedure-trends-widget.component.css']
})
export class ProcedureTrendsWidgetComponent implements OnInit, OnChanges {
    @Input() trends: ProcedureTrends | undefined
    constructor() { }

    options = {
        responsive: true,
        maintainAspectRatio: false,
        tooltips: {
            mode: 'index',
            intersect: false
        },
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

    data : any = {
        labels: ['Month 1', 'Month 2', 'Month 3', 'Month 4', 'Month 5', 'Month 6'],
        datasets: []
    };

    colors = ['#6D71F9','#54C1FB','#272848']

    ngOnChanges(changes: SimpleChanges): void {
        this.updateChart();
    }

    ngOnInit(): void {

    }

    updateChart() {
        if(this.trends){
            const original = JSON.stringify(this.data);
            this.data.labels = this.trends.months;
            this.data.datasets = this.trends.procedures.map((el,index) =>{
                return ({
                    type: 'bar',
                    label: el.name,
                    backgroundColor: this.colors[index],
                    data: el.monthly,
                })
            }
            )

            const current = JSON.stringify(this.data)

            //structure is always the same so we can check differences by using strings
            // if(original != current){
            //     this.refreshChart();
            // }
            this.refreshChart();
        }
    }

    refreshChart(){
        this.data = JSON.parse(JSON.stringify(this.data))
    }

}
