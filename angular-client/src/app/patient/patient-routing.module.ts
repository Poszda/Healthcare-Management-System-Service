import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { NothingHereComponent } from '../core/pages/nothing-here/nothing-here.component';
import { AppointmentsComponent } from './components/appointments/appointments.component';
import { DashboardComponent } from './components/dashboard/dashboard.component';
import { PatientComponent } from './patient.component';
import { PrescriptionsComponent } from './components/prescriptions/prescriptions.component';

const routes: Routes = [
      {path: '', component: PatientComponent, children: [
      { path: '', redirectTo:'dashboard',pathMatch:'prefix'},
      { path: 'dashboard', component: DashboardComponent },
      { path: 'appointments', component: AppointmentsComponent },
      { path: 'prescriptions', component: PrescriptionsComponent },
      { path: 'account', component: NothingHereComponent }, //de ce ma lasa?? nu trebuia sa fie invizbil in modulul asta?
    ]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class PatientRoutingModule { }
