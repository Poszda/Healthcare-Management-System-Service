import { Component, Inject } from '@angular/core';
import { FormArray, FormBuilder, FormControl, FormGroup, FormGroupDirective, Validators } from '@angular/forms';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';

@Component({
  selector: 'app-diagnostic-form',
  templateUrl: './diagnostic-form.component.html',
  styleUrls: ['./diagnostic-form.component.css']
})
export class DiagnosticFormComponent {

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

  constructor(public dialogRef: MatDialogRef<DiagnosticFormComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any,
    private fb: FormBuilder){}


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
      this.dialogRef.close(this.form.getRawValue())
    }
}
