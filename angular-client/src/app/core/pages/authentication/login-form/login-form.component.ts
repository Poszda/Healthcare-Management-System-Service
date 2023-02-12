import { Component, OnInit, Output, EventEmitter } from '@angular/core';

@Component({
  selector: 'app-login-form',
  templateUrl: './login-form.component.html',
  styleUrls: ['./login-form.component.css']
})
export class LoginFormComponent implements OnInit {

  @Output() login : EventEmitter<any> = new EventEmitter();
  @Output() goToSignUp : EventEmitter<any> = new EventEmitter();

  email?: string
  password?:string

  constructor() { }

  ngOnInit(): void {
  }

  logIn(){
    this.login.emit({email:this.email,password:this.password});
  }

  toggleForms(){
    this.goToSignUp.emit();
  }

}
