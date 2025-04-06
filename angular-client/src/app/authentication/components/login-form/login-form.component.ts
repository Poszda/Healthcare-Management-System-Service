import { Component, OnInit, Output, EventEmitter } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-login-form',
  templateUrl: './login-form.component.html',
  styleUrls: ['./login-form.component.css']
})
export class LoginFormComponent implements OnInit {

  @Output() login : EventEmitter<any> = new EventEmitter();
  @Output() goToSignUp : EventEmitter<any> = new EventEmitter();

  form: FormGroup = new FormGroup({
      email: new FormControl('', [Validators.required,Validators.email]),
      password: new FormControl('', [Validators.required]),
  });

  constructor() { }

  ngOnInit(): void {
  }

  logIn(){
    this.login.emit(this.form.getRawValue());
  }

  toggleForms(){
    this.goToSignUp.emit();
  }

}
