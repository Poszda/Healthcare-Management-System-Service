import { Component, EventEmitter, Input, Output } from '@angular/core';
import { AppointmentSuggestion } from 'src/app/patient/models/appointment-suggestion.model';

@Component({
  selector: 'app-appointment-suggestion',
  templateUrl: './appointment-suggestion.component.html',
  styleUrls: ['./appointment-suggestion.component.css']
})
export class AppointmentSuggestionComponent {
  @Input() suggestion: AppointmentSuggestion | undefined
  @Output() suggestionSelection : EventEmitter<any> = new EventEmitter();

  selectHour(hourSelected : string){
    const data  = {
      time: hourSelected,
      date: this.suggestion?.date,
      doctorId:this.suggestion?.doctorId
    }
    this.suggestionSelection.emit(data);
  }
}
