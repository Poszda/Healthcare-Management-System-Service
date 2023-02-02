import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { DoctorRoutingModule } from './doctor-routing.module';
import { DoctorComponent } from './doctor.component';
import { SharedModule } from '../shared/shared.module';
import { MainComponent } from '../shared/components/main/main.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { AppointmentsComponent } from './appointments/appointments.component';


@NgModule({
  declarations: [
    DoctorComponent,
    DashboardComponent,
    AppointmentsComponent,
  ],
  imports: [
    CommonModule,
    DoctorRoutingModule,
    SharedModule,
  ]
})
export class DoctorModule { }
