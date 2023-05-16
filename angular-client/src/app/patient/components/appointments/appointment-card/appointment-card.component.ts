import { Component, EventEmitter, Input, OnChanges, OnInit, Output, SimpleChanges } from '@angular/core';
import * as moment from 'moment';
import { AppointmentCard } from 'src/app/patient/models/appointment-card.model';

@Component({
  selector: 'app-appointment-card',
  templateUrl: './appointment-card.component.html',
  styleUrls: ['./appointment-card.component.css']
})
export class AppointmentCardComponent implements OnInit{
  @Input() appointment : AppointmentCard | undefined
  @Output() deleteAppointment: EventEmitter<number> = new EventEmitter();
  deletionEnabled : boolean = false;
  formatedInterval : string = "";

  ngOnInit(): void {
    this.isFutureAppointment()
    this.formatInterval()
  }

  triggerAppointmentDeletion(){
    this.deleteAppointment.emit(this.appointment?.id);
  }

  formatInterval(){
    this.formatedInterval = moment(this.appointment?.dateTime).add(this.appointment?.duration,'minutes').format('HH:mm') //create a pipe here instead
  }

  isFutureAppointment(){
    this.deletionEnabled =  moment(this.appointment?.dateTime).isAfter(moment())
  }

}
