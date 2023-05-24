import { Component, Input } from '@angular/core';

@Component({
  selector: 'app-widget-simple-container',
  templateUrl: './widget-simple-container.component.html',
  styleUrls: ['./widget-simple-container.component.css']
})
export class WidgetSimpleContainerComponent {

  @Input() title : string = ""
  
  constructor(){}
  
}
