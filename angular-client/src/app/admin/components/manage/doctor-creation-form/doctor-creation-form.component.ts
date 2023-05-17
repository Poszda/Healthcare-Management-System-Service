import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-doctor-creation-form',
  templateUrl: './doctor-creation-form.component.html',
  styleUrls: ['./doctor-creation-form.component.css']
})
export class DoctorCreationFormComponent implements OnInit{
  @Input() specialityOptions : any[] = []
  @Output() createDoctor : EventEmitter<any> = new EventEmitter();
  
  form: FormGroup = new FormGroup({
    firstName: new FormControl(null, [Validators.required]),
    lastName: new FormControl(null, [Validators.required]),
    speciality: new FormControl(null, [Validators.required]),
    email: new FormControl(null, [Validators.required,Validators.email]),
    password: new FormControl(null, [Validators.required]),
    programStart: new FormControl(null, [Validators.required]),
    programEnd: new FormControl(null, [Validators.required]),
  });

  ngOnInit(): void {
  }

  create(){
    this.createDoctor.emit(this.form.getRawValue());
   // this.form.reset();
  }
}
