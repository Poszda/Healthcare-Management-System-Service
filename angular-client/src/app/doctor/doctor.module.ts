import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { DoctorRoutingModule } from './doctor-routing.module';
import { DoctorComponent } from './doctor.component';
import { SharedModule } from '../shared/shared.module';
import { DashboardComponent } from './components/dashboard/dashboard.component';
import { AppointmentsComponent } from './components/appointments/appointments.component';
import { ProgramWidgetComponent } from './components/dashboard/program-widget/program-widget.component';
import { OverallstatusWidgetComponent } from './components/dashboard/overallstatus-widget/overallstatus-widget.component';
import { AppointmentsTableComponent } from './components/appointments/appointments-table/appointments-table.component';
import { AppointmentsService } from './services/appointments.service';
import { StatisticsService } from './services/statistics.service';
import { DoctorService } from './services/doctor.service';
import {LayoutModule} from '@angular/cdk/layout';
import { DiagnosticFormComponent } from './components/appointments/diagnostic-form/diagnostic-form.component';
import { ProcedureTrendsWidgetComponent } from './components/dashboard/procedure-trends-widget/procedure-trends-widget.component';

import { AccountProfileComponent } from './components/account-profile/account-profile.component';
import { EditProfileFormComponent } from './components/account-profile/edit-profile-form/edit-profile-form.component';

@NgModule({
    declarations: [
        DoctorComponent,
        DashboardComponent,
        AppointmentsComponent,
        ProgramWidgetComponent,
        OverallstatusWidgetComponent,
        ProcedureTrendsWidgetComponent,
        AppointmentsTableComponent,
        DiagnosticFormComponent,
        AccountProfileComponent,
        EditProfileFormComponent,
    ],
    imports: [
        CommonModule,
        DoctorRoutingModule,
        SharedModule,
        LayoutModule
    ],
    providers:[
        AppointmentsService,
        StatisticsService,
        DoctorService
      ]
})
export class DoctorModule { }
