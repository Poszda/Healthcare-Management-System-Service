import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { NothingHereComponent } from './pages/nothing-here/nothing-here.component';
import { AuthenticationComponent } from './pages/authentication/authentication.component';
import { HttpClientModule } from '@angular/common/http';

@NgModule({
  declarations: [
    NothingHereComponent,
    AuthenticationComponent
  ],
  imports: [
    CommonModule,
    HttpClientModule,
  ],
})
export class CoreModule { }
