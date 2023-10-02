import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { AppointmentSuggestion } from 'src/app/patient/models/appointment-suggestion.model';
import { AppointmentsService } from 'src/app/patient/services/appointments.service';

@Component({
  selector: 'app-appointment-suggestion',
  templateUrl: './appointment-suggestion.component.html',
  styleUrls: ['./appointment-suggestion.component.css']
})
export class AppointmentSuggestionComponent implements OnInit {
  @Input() suggestion: AppointmentSuggestion | undefined
  @Input() componentIndex : number | undefined
  @Output() suggestionSelection : EventEmitter<any> = new EventEmitter();

  selectedButtonIndex : number | null = null;

  constructor(private appointmentsService : AppointmentsService){}

  ngOnInit(): void {
    this.appointmentsService.resetButtonsColorExcept.subscribe(exceptIndex =>{
      if(exceptIndex !== this.componentIndex) this.selectedButtonIndex = null;
    })
  }

  selectHour(hourSelected : string, index : number){
    const data  = {
      time: hourSelected,
      date: this.suggestion?.date,
      doctorId:this.suggestion?.doctorId
    }
    this.suggestionSelection.emit(data);

    this.selectedButtonIndex = index;
    this.appointmentsService.resetButtonsColorExcept.next(this.componentIndex);
  }
}
