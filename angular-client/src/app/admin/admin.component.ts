import { Component, OnDestroy, OnInit } from '@angular/core';

@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.css']
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

  constructor() { }

  ngOnInit(): void {
  }

  ngOnDestroy(): void {
    console.log('destroyed')
  }
}
