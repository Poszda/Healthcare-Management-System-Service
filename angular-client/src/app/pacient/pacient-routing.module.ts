import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { NothingHereComponent } from '../core/pages/nothing-here/nothing-here.component';
import { AppointmentsComponent } from './appointments/appointments.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { PacientComponent } from './pacient.component';

const routes: Routes = [
      {path: '', component: PacientComponent, children: [
      { path: '', redirectTo:'dashboard',pathMatch:'prefix'},
      { path: 'dashboard', component: DashboardComponent },
      { path: 'appointments', component: AppointmentsComponent },
      { path: 'prescriptions', component: NothingHereComponent },
      { path: 'account', component: NothingHereComponent }, //de ce ma lasa?? nu trebuia sa fie invizbil in modulul asta?
    ]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class PacientRoutingModule { }
