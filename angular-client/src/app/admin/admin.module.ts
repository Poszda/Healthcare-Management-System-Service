import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { AdminRoutingModule } from './admin-routing.module';
import { AdminComponent } from './admin.component';
import { SharedModule } from '../shared/shared.module';
import { StatsComponent } from './components/stats/stats.component';
import { ManageComponent } from './components/manage/manage.component';


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
