import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-signup-form',
  templateUrl: './signup-form.component.html',
  styleUrls: ['./signup-form.component.css']
})
export class SignupFormComponent implements OnInit {
  
  @Output() signup : EventEmitter<any> = new EventEmitter();
  @Output() goToLogin : EventEmitter<any> = new EventEmitter();

  form: FormGroup = new FormGroup({
    email: new FormControl('', [Validators.required,Validators.email]),/* ,Validators.email */
    password: new FormControl('', [Validators.required]),
    rePassword:new FormControl('', [Validators.required]),
  });


  constructor() { }

  ngOnInit(): void {
  }

  signUp(){
    this.signup.emit(/* form */);
  }
  toggleForms(){
    this.goToLogin.emit();
  }

}
