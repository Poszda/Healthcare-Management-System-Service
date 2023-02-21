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


@NgModule({
  declarations: [
    PatientComponent,
    DashboardComponent,
    AppointmentsComponent,
    BioWidgetComponent,
    PrescriptionsWidgetComponent,
    DoctorsWidgetComponent
  ],
  imports: [
    CommonModule,
    PatientRoutingModule,
    SharedModule
  ]
})
export class PatientModule { }
