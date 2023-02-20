import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-appointments-widget',
  templateUrl: './appointments-widget.component.html',
  styleUrls: ['./appointments-widget.component.css']
})
export class AppointmentsWidgetComponent implements OnInit {
  appointments = [1,2,3,4,5,6,7 ]
  constructor() { }

  ngOnInit(): void {
  }

}
