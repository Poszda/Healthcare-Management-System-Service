import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { NothingHereComponent } from './pages/nothing-here/nothing-here.component';
import { AuthenticationComponent } from './pages/authentication/authentication.component';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { LoginFormComponent } from './pages/authentication/login-form/login-form.component';
import { SignupFormComponent } from './pages/authentication/signup-form/signup-form.component';

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
    FormsModule
  ],
})
export class CoreModule { }
