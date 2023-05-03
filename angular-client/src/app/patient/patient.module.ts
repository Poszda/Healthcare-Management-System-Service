import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { PatientRoutingModule } from './patient-routing.module';
import { PatientComponent } from './patient.component';
import { SharedModule } from '../shared/shared.module';
import { DashboardComponent } from './dashboard/dashboard.component';
import { AppointmentsComponent } from './appointments/appointments.component';
import { BioWidgetComponent } from './dashboard/bio-widget/bio-widget.component';
import { PrescriptionsWidgetComponent } from './dashboard/prescriptions-widget/prescriptions-widget.component';
import { DoctorsWidgetComponent } from './dashboard/doctors-widget/doctors-widget.component';
import { PrescriptionsComponent } from './prescriptions/prescriptions.component';
import { PrescriptionCardComponent } from './prescriptions/prescription-card/prescription-card.component';
import { AppointmentCardComponent } from './appointments/appointment-card/appointment-card.component';
import { AppointmentFormComponent } from './appointments/appointment-form/appointment-form.component';
import { AppointmentSuggestionComponent } from './appointments/appointment-form/appointment-suggestion/appointment-suggestion.component';
import { AppointmentLastStepComponent } from './appointments/appointment-form/appointment-last-step/appointment-last-step.component';
import { AppointmentsService } from './services/appointments.service';
import { SpecialitiesService } from './services/specialities.service';


@NgModule({
  declarations: [
    PatientComponent,
    DashboardComponent,
    AppointmentsComponent,
    BioWidgetComponent,
    PrescriptionsWidgetComponent,
    DoctorsWidgetComponent,
    PrescriptionsComponent,
    PrescriptionCardComponent,
    AppointmentCardComponent,
    AppointmentFormComponent,
    AppointmentSuggestionComponent,
    AppointmentLastStepComponent,
  ],
  imports: [
    CommonModule,
    PatientRoutingModule,
    SharedModule,
  ],
  providers:[
    AppointmentsService,
    SpecialitiesService
  ]
})
export class PatientModule { }
