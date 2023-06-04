import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { NothingHereComponent } from './pages/nothing-here/nothing-here.component';
import { AuthenticationComponent } from './pages/authentication/authentication.component';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { LoginFormComponent } from './pages/authentication/login-form/login-form.component';
import { SignupFormComponent } from './pages/authentication/signup-form/signup-form.component';
import {MatInputModule} from '@angular/material/input';
import { InputTextModule } from 'primeng/inputtext';
import {ToastModule} from 'primeng/toast';
import { CalendarModule } from 'primeng/calendar';

@NgModule({
  declarations: [
    NothingHereComponent,
    AuthenticationComponent,
    LoginFormComponent,
    SignupFormComponent
  ],
  imports: [
    CommonModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule,
    InputTextModule,
    CalendarModule,
    ToastModule,
  ],
})
export class CoreModule { }
