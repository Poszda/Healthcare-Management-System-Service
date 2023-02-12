import { Component, OnInit } from '@angular/core';
import { UserService } from '../../services/user.service';

@Component({
  selector: 'app-authentication',
  templateUrl: './authentication.component.html',
  styleUrls: ['./authentication.component.css']
})
export class AuthenticationComponent implements OnInit {

  showLogInForm : boolean = true;
  showSignUpForm : boolean = false;

  constructor(private userService: UserService) { }

  ngOnInit(): void {
  }

  login(form : any){
    console.log(form)
    this.userService.login(form.email,form.password);
  }

  signup(form: any){
    console.log(form)
    //this.userService.signup();
  }

  onGoToSignUp(){
    this.showLogInForm = false;
    this.showSignUpForm = true;
  }

  onGoToLogIn(){
    this.showLogInForm = true;
    this.showSignUpForm = false;
  }
  
}
