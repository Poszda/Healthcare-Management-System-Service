import { Component, Input, OnInit } from '@angular/core';

@Component({
  selector: 'app-widget-link-container',
  templateUrl: './widget-link-container.component.html',
  styleUrls: ['./widget-link-container.component.css']
})
export class WidgetLinkContainerComponent implements OnInit {

  @Input() title = 'Default link widget title'
  @Input() link = "./"
  constructor() { }

  ngOnInit(): void {
  }

}
