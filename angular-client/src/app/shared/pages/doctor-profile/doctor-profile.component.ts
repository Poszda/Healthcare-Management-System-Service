import { Component,OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-doctor-profile',
  templateUrl: './doctor-profile.component.html',
  styleUrls: ['./doctor-profile.component.css']
})
export class DoctorProfileComponent implements OnInit {

  constructor( private route: ActivatedRoute){}

  ngOnInit(): void {
    this.route.params.subscribe(
      res =>{
        console.log(res)
      },
      err =>{
        console.log(err)
      }
    )
  }

}
