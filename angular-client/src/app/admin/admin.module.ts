import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { AdminRoutingModule } from './admin-routing.module';
import { AdminComponent } from './admin.component';
import { SharedModule } from '../shared/shared.module';
import { StatsComponent } from './stats/stats.component';
import { ManageComponent } from './manage/manage.component';


@NgModule({
  declarations: [
    AdminComponent,
    StatsComponent,
    ManageComponent
  ],
  imports: [
    CommonModule,
    AdminRoutingModule,
    SharedModule
  ]
})
export class AdminModule { }
