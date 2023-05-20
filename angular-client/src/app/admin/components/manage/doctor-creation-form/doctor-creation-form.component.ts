import { Component, EventEmitter, Input, OnInit, Output,Inject } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { HospitalManagementService } from 'src/app/admin/services/hospital-management.service';
import { newDoctor } from 'src/app/admin/models/new-doctor.model';
import { StatisticsService } from 'src/app/admin/services/statistics.service';
import { AlertService } from 'src/app/core/services/alert.service';

@Component({
  selector: 'app-doctor-creation-form',
  templateUrl: './doctor-creation-form.component.html',
  styleUrls: ['./doctor-creation-form.component.css'],
})
export class DoctorCreationFormComponent implements OnInit{
  specialityOptions : any[] = []
  hospitalId : number = 0;
  
  form: FormGroup = new FormGroup({
    firstName: new FormControl(null, [Validators.required]),
    lastName: new FormControl(null, [Validators.required]),
    speciality: new FormControl(null, [Validators.required]),
    email: new FormControl(null, [Validators.required,Validators.email]),
    password: new FormControl(null, [Validators.required]),
    programStart: new FormControl(null, [Validators.required]),
    programEnd: new FormControl(null, [Validators.required]),
  });

  constructor(private alertService : AlertService, private hospitalManagementService:HospitalManagementService, public dialogRef: MatDialogRef<DoctorCreationFormComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any,
    ){}

  ngOnInit(): void {
    this.specialityOptions = this.data.specialities
    this.hospitalId = this.data.hospitalId
  }

  create(){
    const form = this.form.getRawValue();
    const newDoctor : newDoctor = {
      firstName: form.firstName,
      lastName: form.lastName,
      email:form.email,
      password: form.password,
      specialityId: form.speciality,
      programEnd: form.programEnd,
      programStart: form.programStart,
      hospitalId: this.hospitalId
    }

    this.hospitalManagementService.createDoctor(newDoctor).subscribe(
      res =>{
        this.alertService.showSuccess('Doctor created successfully')
        this.dialogRef.close(res);
      },
      err =>{
        console.log(err)
        if('error' in err && typeof err.error === "string") this.alertService.showError(err.error)
        else this.alertService.showError('Something went wrong')
      }
    )
  }
  closeDialog(){
    this.dialogRef.close()
  }
}
