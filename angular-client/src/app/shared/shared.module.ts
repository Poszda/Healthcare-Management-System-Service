import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { NavbarComponent } from './components/navbar/navbar.component';
import { RouterModule } from '@angular/router';
import { WidgetDotContainerComponent } from './components/widgets-containers/widget-dot-container/widget-dot-container.component';
import { WidgetLinkContainerComponent } from './components/widgets-containers/widget-link-container/widget-link-container.component';
import {ChartModule} from 'primeng/chart';
import { WidgetSimpleContainerComponent } from './components/widgets-containers/widget-simple-container/widget-simple-container.component';
import { AppointmentsWidgetComponent } from './components/widgets/appointments-widget/appointments-widget.component';

import {MatSnackBarModule} from '@angular/material/snack-bar';
import {MatDialogModule} from '@angular/material/dialog';
import {MatInputModule} from '@angular/material/input';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import {MatAutocompleteModule} from '@angular/material/autocomplete';

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

    MatSnackBarModule,
    MatDialogModule,
    MatInputModule,
    MatAutocompleteModule
    
  ],
  exports:[
    FormsModule, //??
    ReactiveFormsModule, //?
    NavbarComponent,
    WidgetDotContainerComponent,
    WidgetLinkContainerComponent,
    AppointmentsWidgetComponent,
    ChartModule,// should? it could but meh
    MatSnackBarModule,
    MatDialogModule, // should?
    MatInputModule,
    MatAutocompleteModule
  ]
})
export class SharedModule { }
