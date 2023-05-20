import { Component, OnDestroy, OnInit } from '@angular/core';
import { MessageService } from 'primeng/api';
import { StatisticsService } from './services/statistics.service';
import { AlertService } from '../core/services/alert.service';

@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.css'],
  providers:[MessageService]
})
export class AdminComponent implements OnInit, OnDestroy {

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

  constructor(private messageService: MessageService,private alertService : AlertService) { }

  ngOnInit(): void {
    this.alertService.alert.subscribe(
      alert =>{
        this.showAlert(alert)
      }
    )
  }

  ngOnDestroy(): void {
    console.log('destroyed')
  }

  showAlert(alert : any){
    if(alert.type === "success") this.messageService.add({ severity: 'success', summary: 'Success', detail: alert.message });
    if(alert.type === "error") this.messageService.add({ severity: 'error', summary: 'Error', detail: alert.message });
  }
  

}
