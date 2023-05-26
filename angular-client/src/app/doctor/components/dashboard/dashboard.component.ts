import { Component, OnInit } from '@angular/core';
import { AppointmentsService } from '../../services/appointments.service';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {

  doctorId : number = 0;
  constructor() { }

  ngOnInit(): void {

  }

}
