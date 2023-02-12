import { Component, EventEmitter, OnInit, Output } from '@angular/core';

@Component({
  selector: 'app-signup-form',
  templateUrl: './signup-form.component.html',
  styleUrls: ['./signup-form.component.css']
})
export class SignupFormComponent implements OnInit {
  
  @Output() signup : EventEmitter<any> = new EventEmitter();
  @Output() goToLogin : EventEmitter<any> = new EventEmitter();

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
