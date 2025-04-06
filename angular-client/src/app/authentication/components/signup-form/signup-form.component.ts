import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import {FormControl, FormGroup, Validators } from '@angular/forms';
import * as moment from 'moment';
import { SignUpForm } from '../../../core/models/signup-form.model';
import { passwordMatch} from 'src/app/shared/utils/validators/password-match.validator';

@Component({
  selector: 'app-signup-form',
  templateUrl: './signup-form.component.html',
  styleUrls: ['./signup-form.component.css']
})
export class SignupFormComponent implements OnInit {

  @Output() signup: EventEmitter<SignUpForm> = new EventEmitter();
  @Output() goToLogin: EventEmitter<any> = new EventEmitter();
  defaultDate: Date = moment('2000/01/01').toDate();
  maxDate: Date = moment().toDate()

  form: FormGroup = new FormGroup({
    email: new FormControl('', [Validators.required, Validators.email]),
    firstName: new FormControl('', [Validators.required]),
    lastName: new FormControl('', [Validators.required]),
    birthDate: new FormControl('', [Validators.required]),
    password: new FormControl('', [Validators.required, Validators.minLength(4)]),
    rePassword: new FormControl('', [Validators.required]),
  }, { validators: [passwordMatch('password','rePassword')] });

  get email() {return this.form.get('email');}
  get firstName() {return this.form.get('firstName');}
  get lastName() {return this.form.get('lastName');}
  get password() {return this.form.get('password');}
  get rePassword() {return this.form.get('rePassword');}

  constructor() { }

  ngOnInit(): void {
  }

  signUp() {
    const form: SignUpForm = this.form.getRawValue();
    form.birthDate = moment(form.birthDate).format('YYYY-MM-DD');
    this.signup.emit(form);
  }
  toggleForms() {
    this.goToLogin.emit();
  }

}
