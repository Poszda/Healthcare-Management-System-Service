import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { NavbarComponent } from './components/navbar/navbar.component';
import { RouterModule } from '@angular/router';
import { WidgetDotContainerComponent } from './components/widgets-containers/widget-dot-container/widget-dot-container.component';
import { WidgetLinkContainerComponent } from './components/widgets-containers/widget-link-container/widget-link-container.component';
import {MatSnackBarModule} from '@angular/material/snack-bar';
import {ChartModule} from 'primeng/chart';
import { WidgetSimpleContainerComponent } from './components/widgets-containers/widget-simple-container/widget-simple-container.component';

@NgModule({
  declarations: [
    NavbarComponent,
    WidgetDotContainerComponent,
    WidgetLinkContainerComponent,
    WidgetSimpleContainerComponent,
  ],
  imports: [
    CommonModule,
    RouterModule, 
    MatSnackBarModule,
    ChartModule //prime ng
    
  ],
  exports:[
    NavbarComponent,
    WidgetDotContainerComponent,
    WidgetLinkContainerComponent,
    ChartModule // should?
  ]
})
export class SharedModule { }
