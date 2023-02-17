import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { NavbarComponent } from './components/main/navbar/navbar.component';
import { MainComponent } from './components/main/main.component';
import { RouterModule } from '@angular/router';
import { WidgetDotContainerComponent } from './components/widgets-containers/widget-dot-container/widget-dot-container.component';
import { WidgetLinkContainerComponent } from './components/widgets-containers/widget-link-container/widget-link-container.component';

@NgModule({
  declarations: [
    MainComponent,
    NavbarComponent,
    WidgetDotContainerComponent,
    WidgetLinkContainerComponent,
  ],
  imports: [
    CommonModule,
    RouterModule // ??
  ],
  exports:[
    MainComponent,
    NavbarComponent,
    WidgetDotContainerComponent,
    WidgetLinkContainerComponent
  ]
})
export class SharedModule { }
