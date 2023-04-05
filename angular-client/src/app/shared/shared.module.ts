import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { NavbarComponent } from './components/navbar/navbar.component';
import { RouterModule } from '@angular/router';
import { WidgetDotContainerComponent } from './components/widgets-containers/widget-dot-container/widget-dot-container.component';
import { WidgetLinkContainerComponent } from './components/widgets-containers/widget-link-container/widget-link-container.component';
import {ChartModule} from 'primeng/chart';
import { WidgetSimpleContainerComponent } from './components/widgets-containers/widget-simple-container/widget-simple-container.component';
import { AppointmentsWidgetComponent } from './components/widgets/appointments-widget/appointments-widget.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { CalendarModule } from 'primeng/calendar';
import {InputTextModule} from 'primeng/inputtext';
import {AutoCompleteModule} from 'primeng/autocomplete';
import {ToastModule} from 'primeng/toast';
import {MultiSelectModule} from 'primeng/multiselect';
import {DropdownModule} from 'primeng/dropdown';
import {ProgressSpinnerModule} from 'primeng/progressspinner';

import {MatDialogModule} from '@angular/material/dialog';
import {MatStepperModule} from '@angular/material/stepper';
import {MatProgressBarModule} from '@angular/material/progress-bar';

@NgModule({
  declarations: [
    NavbarComponent,
    WidgetDotContainerComponent,
    WidgetLinkContainerComponent,
    WidgetSimpleContainerComponent,
    AppointmentsWidgetComponent
  ],
  imports: [
    CommonModule,
    RouterModule, 
    FormsModule,
    ReactiveFormsModule,

    ChartModule, //prime ng
    CalendarModule,
    InputTextModule,
    AutoCompleteModule,
    ToastModule,
    MatDialogModule,
    MatStepperModule,
    MultiSelectModule,
    DropdownModule,
    ProgressSpinnerModule,
    MatProgressBarModule

    
  ],
  exports:[
    FormsModule, //??
    ReactiveFormsModule, //?
    NavbarComponent,
    WidgetDotContainerComponent,
    WidgetLinkContainerComponent,
    AppointmentsWidgetComponent,
    ChartModule,// should? it could but meh
    MatDialogModule, // should?
    CalendarModule,
    InputTextModule,
    AutoCompleteModule,
    ToastModule,
    MatStepperModule,
    MultiSelectModule,
    DropdownModule,
    ProgressSpinnerModule,
    MatProgressBarModule
    
    
  ]
})
export class SharedModule { }
