import { Component, OnInit } from '@angular/core';
import { UserType } from 'src/app/core/models/user-type.enum';
import { UserService } from 'src/app/core/services/user.service';

@Component({
  selector: 'app-main',
  templateUrl: './main.component.html',
  styleUrls: ['./main.component.css']
})
export class MainComponent implements OnInit {

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

  doctorRoutes : any = [
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
      name:'Diagnostics',
      route:'./prescriptions',
      icon:'/assets/icons/diagnostics.png'
    },
    {
      name:'Account',
      route:'./account',
      icon:'/assets/icons/account2.png' 
    }
  ]

  adminRoutes : any = [
    {
      name:'Statistics',
      route:'./stats',
      icon:'/assets/icons/stats.png'
    },
    {
      name:'Manage',
      route:'./manage',
      icon:'/assets/icons/manage3.png'
    },
  ]

  constructor(private userService:UserService) { }

  ngOnInit(): void {
  }
  
  getNeededRoutes(){
    const type  = this.userService.getUserType();
    if(type === UserType.Admin) return this.adminRoutes;
    if(type === UserType.Doctor) return this.doctorRoutes ;
    else if(type === UserType.Patient) return this.pacientRoutes;
  }

}
