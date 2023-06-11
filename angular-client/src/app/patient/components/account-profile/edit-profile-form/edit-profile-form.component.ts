import { Component, Inject } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import * as moment from 'moment';
import { BloodType } from 'src/app/shared/models/blood-type.enum';

@Component({
  selector: 'app-edit-profile-form',
  templateUrl: './edit-profile-form.component.html',
  styleUrls: ['./edit-profile-form.component.css']
})
export class EditProfileFormComponent {
  constructor(public dialogRef: MatDialogRef<EditProfileFormComponent>,
    @Inject(MAT_DIALOG_DATA) public data: {}) {
      this.form.patchValue(data)
  }

  defaultDate : Date = moment('2000-01-01').toDate();
  maxDate : Date = moment().toDate()

  bloodOptions : any[] = [null,...Object.values(BloodType)];

  form: FormGroup = new FormGroup({
    firstName: new FormControl(null, Validators.required),
    lastName: new FormControl(null, Validators.required),
    height: new FormControl(null),
    weight: new FormControl(null),
    bloodType: new FormControl(null),
    phone: new FormControl(null,),
    birthDate: new FormControl(null, Validators.required),
  });

  edit() {
    this.dialogRef.close(this.form.getRawValue())
  }
}
