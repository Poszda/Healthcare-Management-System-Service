import { Component, OnInit } from '@angular/core';
import { AuthService } from 'src/app/core/services/auth.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-nothing-here',
  templateUrl: './nothing-here.component.html',
  styleUrls: ['./nothing-here.component.css']
})
export class NothingHereComponent implements OnInit {

  constructor(private router: Router,private authService: AuthService) { }

  ngOnInit(): void {
  }

  navigate(){
    const route = this.authService.getUserRoute()? this.authService.getUserRoute()! : 'authentication';
    this.router.navigateByUrl(route);
  }

}
