import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { PatientRoutingModule } from './patient-routing.module';
import { PatientComponent } from './patient.component';
import { SharedModule } from '../shared/shared.module';
import { DashboardComponent } from './components/dashboard/dashboard.component';
import { AppointmentsComponent } from './components/appointments/appointments.component';
import { BioWidgetComponent } from './components/dashboard/bio-widget/bio-widget.component';
import { PrescriptionsWidgetComponent } from './components/dashboard/prescriptions-widget/prescriptions-widget.component';
import { DoctorsWidgetComponent } from './components/dashboard/doctors-widget/doctors-widget.component';
import { PrescriptionsComponent } from './components/prescriptions/prescriptions.component';
import { PrescriptionCardComponent } from './components/prescriptions/prescription-card/prescription-card.component';
import { AppointmentCardComponent } from './components/appointments/appointment-card/appointment-card.component';
import { AppointmentFormComponent } from './components/appointments/appointment-form/appointment-form.component';
import { AppointmentSuggestionComponent } from './components/appointments/appointment-form/appointment-suggestion/appointment-suggestion.component';
import { AppointmentLastStepComponent } from './components/appointments/appointment-form/appointment-last-step/appointment-last-step.component';
import { AppointmentsService } from './services/appointments.service';
import { SpecialitiesService } from './services/specialities.service';
import { UserService } from './services/user.service';
import { HospitalsService } from './services/hospitals.service';


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
    SpecialitiesService,
    HospitalsService,
    UserService
  ]
})
export class PatientModule { }
