import { Component, OnInit } from '@angular/core';
import { UserService } from '../../services/user.service';
import { SignUpForm } from './models/signup-form.model';
import { LoginForm } from './models/login-form.model';
import { MessageService } from 'primeng/api';


@Component({
  selector: 'app-authentication',
  templateUrl: './authentication.component.html',
  styleUrls: ['./authentication.component.css'],
  providers: [MessageService]
})
export class AuthenticationComponent implements OnInit {

  showLogInForm : boolean = true;
  showSignUpForm : boolean = false;

  constructor(private userService: UserService, private messageService : MessageService) { }

  ngOnInit(): void {
  }

  login(form : LoginForm){
    this.userService.login(form).subscribe(
      res =>{
        this.userService.handleLogin(res);
      },
      err =>{
        if (err.status !== 0){ //if backend works
          this.showError(err.error);
        }
        else{
          this.showError("Something went wrong")
        }
      }
    );
  }

  signup(form: SignUpForm){
    this.userService.signUp(form).subscribe(
      res =>{
        this.onGoToLogIn();
        this.showSuccess();
      },
      err =>{
        if (err.status !== 0){ //if backend works
          this.showError(err.error);
        }
        else{
          this.showError("Something went wrong")
        }
      }
    );
  }

  onGoToSignUp(){
    this.showLogInForm = false;
    this.showSignUpForm = true;
  }

  onGoToLogIn(){
    this.showLogInForm = true;
    this.showSignUpForm = false;
  }

  showSuccess() {
    this.messageService.add({ severity: 'success', summary: 'Success', detail: 'Account registered succesfully' });
  }

  showError(message : string) {
    this.messageService.add({ severity: 'error', summary: 'Error', detail: message });
  }
  
}
