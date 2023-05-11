import { Component, EventEmitter, Input, Output } from '@angular/core';
import { AppointmentSummary } from 'src/app/patient/models/appointment-summary.model';

@Component({
  selector: 'app-appointment-last-step',
  templateUrl: './appointment-last-step.component.html',
  styleUrls: ['./appointment-last-step.component.css']
})
export class AppointmentLastStepComponent {
  @Input() summary : AppointmentSummary | undefined;
  constructor(){}


}
