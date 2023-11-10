import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AuthGuardService } from './core/guards/auth-guard.service';
import { AuthenticationComponent } from './core/pages/authentication/authentication.component';
import { NothingHereComponent } from './core/pages/nothing-here/nothing-here.component';

const routes: Routes = [
  { path: 'admin', canActivate:[AuthGuardService], canLoad:[AuthGuardService], loadChildren: () => import('./admin/admin.module').then(m => m.AdminModule) },
  { path: 'patient', canActivate:[AuthGuardService], canLoad:[AuthGuardService], loadChildren: () => import('./patient/patient.module').then(m => m.PatientModule) },
  { path: 'doctor', canActivate:[AuthGuardService],canLoad:[AuthGuardService], loadChildren: () => import('./doctor/doctor.module').then(m => m.DoctorModule) },
  { path:'authentication', component: AuthenticationComponent},
  { path: '', redirectTo:'authentication', pathMatch:'full'}, // addded new
  { path: 'not-found', component:NothingHereComponent},
  { path: '**', redirectTo:'not-found'}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
