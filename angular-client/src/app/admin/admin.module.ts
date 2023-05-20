import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { AdminRoutingModule } from './admin-routing.module';
import { AdminComponent } from './admin.component';
import { SharedModule } from '../shared/shared.module';
import { StatsComponent } from './components/stats/stats.component';
import {ManageComponent } from './components/manage/manage.component';
import {MatTreeModule} from '@angular/material/tree';
import {MatCheckboxModule} from '@angular/material/checkbox';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatIconModule} from '@angular/material/icon';
import {MatButtonModule} from '@angular/material/button';
import { HospitalManagementService } from './services/hospital-management.service';
import { StatisticsService } from './services/statistics.service';
import { ChecklistDatabase, ProceduresTreeComponent } from './components/manage/procedures-tree/procedures-tree.component';
import { DoctorCreationFormComponent } from './components/manage/doctor-creation-form/doctor-creation-form.component';
import { DoctorsTableComponent } from './components/manage/doctors-table/doctors-table.component';


@NgModule({
  declarations: [
    AdminComponent,
    StatsComponent,
    ManageComponent,
    ProceduresTreeComponent,
    DoctorCreationFormComponent,
    DoctorsTableComponent
  ],
  imports: [
    CommonModule,
    AdminRoutingModule,
    SharedModule,

    MatFormFieldModule,
    MatCheckboxModule,
    MatTreeModule,
    MatIconModule,
    MatButtonModule,
  ],
  providers:[
    HospitalManagementService,
    StatisticsService,
    ChecklistDatabase
  ]
})
export class AdminModule { }
