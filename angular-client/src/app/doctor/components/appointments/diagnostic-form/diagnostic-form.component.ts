import { Component, Inject } from '@angular/core';
import { FormArray, FormBuilder, FormControl, FormGroup, FormGroupDirective, Validators } from '@angular/forms';
import { MAT_DIALOG_DATA, MatDialog, MatDialogRef } from '@angular/material/dialog';
import { DoctorAppointmentTabelData } from 'src/app/doctor/models/doctor-appointments.model';
import { ConfirmationDialogComponent } from 'src/app/shared/components/confirmation-dialog/confirmation-dialog.component';

@Component({
  selector: 'app-diagnostic-form',
  templateUrl: './diagnostic-form.component.html',
  styleUrls: ['./diagnostic-form.component.css']
})
export class DiagnosticFormComponent {
  patientText

  form: FormGroup = this.fb.group({
    diagnostic: ["", [Validators.required]],
    description: ["", [Validators.required]],
    medications : this.fb.array([])
  });

  get medications() {
    return this.form.get('medications') as FormArray;
  }
  get medicationsControls() : FormGroup[] {
    return (this.form.get('medications') as FormArray).controls as FormGroup[]
  }

  constructor(public dialog : MatDialog, public dialogRef: MatDialogRef<DiagnosticFormComponent>,
    @Inject(MAT_DIALOG_DATA) public data: DoctorAppointmentTabelData,
    private fb: FormBuilder){
     
      this.patientText = `to ${data.name}, from ${data.date}, ${data.time}`
    }


    addMedication(){
      const medication = this.fb.group({
        name:["",[Validators.required]],
        numberOfDays : ["",[Validators.required]],
        dose : ["",[Validators.required]]
      });

      this.medications.push(medication);
    }

    removeMedication(){
      this.medications.removeAt(this.medications.length - 1);
    } 

    create(){
      this.dialog.open(ConfirmationDialogComponent,{
        panelClass: 'doctor-creation-form-dialog',
        maxHeight:'80vh',
        width:'600px',
        data : {message: "Are you sure you want to save the diagnostic? Please confirm that the patient details are accurate before proceeding."}
      }).afterClosed().subscribe(
        status =>{
          if(status){
            this.dialogRef.close(this.form.getRawValue())
          }
        }
      )
      
    }
}
