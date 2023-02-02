import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { NavbarComponent } from './components/main/navbar/navbar.component';
import { MainComponent } from './components/main/main.component';
import { RouterModule } from '@angular/router';

@NgModule({
  declarations: [
    MainComponent,
    NavbarComponent,
  ],
  imports: [
    CommonModule,
    RouterModule // ??
  ],
  exports:[
    MainComponent,
    NavbarComponent
  ]
})
export class SharedModule { }
