import { Component, OnInit } from '@angular/core';
import { MessageService } from 'primeng/api';
import { AlertService } from '../core/services/alert.service';

@Component({
  selector: 'app-doctor',
  templateUrl: './doctor.component.html',
  styleUrls: ['./doctor.component.css'],
  providers:[MessageService]
})
export class DoctorComponent implements OnInit {

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
      name:'Account',
      route:'./account',
      icon:'/assets/icons/account2.png' 
    }
  ]
  
  constructor(private alertService : AlertService, private messageService : MessageService) { }

  ngOnInit(): void {
    this.alertService.alert.subscribe(
      alert =>{
        this.showAlert(alert)
      }
    )
  }

  showAlert(alert : any){
    if(alert.type === "success") this.messageService.add({ severity: 'success', summary: 'Success', detail: alert.message });
    if(alert.type === "error") this.messageService.add({ severity: 'error', summary: 'Error', detail: alert.message });
  }

}
