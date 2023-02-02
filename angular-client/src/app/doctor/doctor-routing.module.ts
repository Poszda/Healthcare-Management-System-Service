import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { DashboardComponent } from './dashboard/dashboard.component';
import { DoctorComponent } from './doctor.component';
import { NothingHereComponent } from '../core/pages/nothing-here/nothing-here.component';
import { AppointmentsComponent } from './appointments/appointments.component';

const routes: Routes = [
  { path: '', component: DoctorComponent,children:[
    {path:'dashboard', component:DashboardComponent},
    {path:'appointments', component: AppointmentsComponent},
    {path:'diagnostics', component: NothingHereComponent},
    {path:'account', component: NothingHereComponent}, //de ce ma lasa?? nu trebuia sa fie invizbil in modulul asta?
  ]}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class DoctorRoutingModule { }
