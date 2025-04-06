import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { NothingHereComponent } from '../shared/pages/nothing-here/nothing-here.component';
import { AdminComponent } from './admin.component';
import { StatsComponent } from './components/stats/stats.component';
import { ManageComponent } from './components/manage/manage.component';

const routes: Routes = [
  {
    path: '', component: AdminComponent, children: [
      { path: '', redirectTo: 'stats', pathMatch: 'prefix' },
      { path: 'stats', component: StatsComponent },
      { path: 'manage', component: ManageComponent },
      { path: 'account', component: NothingHereComponent },
    ]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AdminRoutingModule { }
