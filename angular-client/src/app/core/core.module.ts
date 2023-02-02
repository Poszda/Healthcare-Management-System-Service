import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { NothingHereComponent } from './pages/nothing-here/nothing-here.component';
import { AuthenticationComponent } from './pages/authentication/authentication.component';

@NgModule({
  declarations: [
    NothingHereComponent,
    AuthenticationComponent
  ],
  imports: [
    CommonModule
  ],
})
export class CoreModule { }
