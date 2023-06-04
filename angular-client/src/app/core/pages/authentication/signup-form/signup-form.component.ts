import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { AbstractControl, FormControl, FormGroup, ValidationErrors, ValidatorFn, Validators } from '@angular/forms';
import * as moment from 'moment';
import { SignUpForm } from 'src/app/core/pages/authentication/models/signup-form.model';

/** Check if passwords are matching */
// const passwordMatchingValidator: ValidatorFn = (control: AbstractControl): ValidationErrors | null => {
//   const password = control.parent!.get('password')?.value; //control.parent is null
//   const rePassword = control.value;
//   return password && rePassword && password !== rePassword ? { passwordMissmatch: true } : null;
// };


@Component({
  selector: 'app-signup-form',
  templateUrl: './signup-form.component.html',
  styleUrls: ['./signup-form.component.css']
})
export class SignupFormComponent implements OnInit {
   
  @Output() signup : EventEmitter<SignUpForm> = new EventEmitter();
  @Output() goToLogin : EventEmitter<any> = new EventEmitter();
  defaultDate : Date = moment('2000/01/01').toDate();
  maxDate : Date = moment().toDate()

  form: FormGroup = new FormGroup({
    email: new FormControl('', [Validators.required,Validators.email]),
    firstName:new FormControl('', [Validators.required]),
    lastName:new FormControl('', [Validators.required]),
    password: new FormControl('', [Validators.required,Validators.minLength(4)]),
    rePassword:new FormControl('', [Validators.required,Validators.minLength(4)]),
    birthDate : new FormControl('', [Validators.required])
  });


  constructor() { }

  ngOnInit(): void {
  }

  signUp(){
    this.signup.emit(this.form.getRawValue());
  }
  toggleForms(){
    this.goToLogin.emit();
  }

}
