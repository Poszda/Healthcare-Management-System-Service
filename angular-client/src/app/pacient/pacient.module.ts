import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { PacientRoutingModule } from './pacient-routing.module';
import { PacientComponent } from './pacient.component';
import { SharedModule } from '../shared/shared.module';
import { DashboardComponent } from './dashboard/dashboard.component';
import { AppointmentsComponent } from './appointments/appointments.component';


@NgModule({
  declarations: [
    PacientComponent,
    DashboardComponent,
    AppointmentsComponent
  ],
  imports: [
    CommonModule,
    PacientRoutingModule,
    SharedModule
  ]
})
export class PacientModule { }
