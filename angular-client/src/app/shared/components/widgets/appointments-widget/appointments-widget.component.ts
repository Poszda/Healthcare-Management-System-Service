import { Component, OnInit,Input } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import * as moment from 'moment';
import { AppointmentNext } from 'src/app/shared/models/appointment-next.model';

@Component({
  selector: 'app-appointments-widget',
  templateUrl: './appointments-widget.component.html',
  styleUrls: ['./appointments-widget.component.css']
})
export class AppointmentsWidgetComponent {
  @Input() title : string = ''
  @Input() appointments : AppointmentNext[] = []
  @Input() displayDate : boolean = false;
  @Input() type : string  = 'doctor'
  constructor(private router : Router) { }

  getTimeInterval(start : string ,duration : string){ // change with pipe
    const date = moment(start);
    if(this.displayDate) return `${date.format('HH:mm')} - ${date.add(duration,'minutes').format('HH:mm')}, ${date.format('MMMM')} ${date.date()}`;
    return `${date.format('HH:mm')} - ${date.add(duration,'minutes').format('HH:mm')} `;
  }

  navigate(id : number){
    let link;
    if(this.type === 'doctor') link = '/doctor/patients/'
    else link = 'patient/doctors/'
    console.log(link + id)
    this.router.navigateByUrl(link + id);
  }


}
