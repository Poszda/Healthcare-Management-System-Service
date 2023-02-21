import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-pacient',
  templateUrl: './patient.component.html',
  styleUrls: ['./patient.component.css']
})
export class PatientComponent implements OnInit {

  pacientRoutes : any = [
    {
      name:'Dashboard',
      route:'./dashboard',
      icon:'/assets/icons/dashboard.png'
    },
    {
      name:'Appointments',
      route:'./appointments',
      icon:'/assets/icons/appointment.png'
    },
    {
      name:'Prescriptions',
      route:'./prescriptions',
      icon:'/assets/icons/prescription.png'
    },
    {
      name:'Account',
      route:'./account',
      icon:'/assets/icons/account2.png' 
    }
  ]
  
  constructor() { }

  ngOnInit(): void {
  }

}
