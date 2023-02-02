import { Component, Input, OnInit, ViewChild } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {

  @Input() routes : any = []
  
  constructor(private router:Router) { }

  ngOnInit(): void {
    console.log(this.router.url)
  }

  fun(event : any){
    console.log(event)
  }
}
