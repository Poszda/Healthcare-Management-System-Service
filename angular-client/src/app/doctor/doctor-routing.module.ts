import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { DashboardComponent } from './components/dashboard/dashboard.component';
import { DoctorComponent } from './doctor.component';
import { AppointmentsComponent } from './components/appointments/appointments.component';
import { PatientProfileComponent } from '../shared/pages/patient-profile/patient-profile.component';
import { AccountProfileComponent } from './components/account-profile/account-profile.component';

const routes: Routes = [
  { path: '', component: DoctorComponent,children:[
    { path: '', redirectTo:'dashboard',pathMatch:'prefix'},
    {path:'dashboard', component:DashboardComponent},
    {path:'appointments', component: AppointmentsComponent},
    {path:'account', component: AccountProfileComponent},
    {path:'patients/:id', component: PatientProfileComponent},
  ]}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class DoctorRoutingModule { }
