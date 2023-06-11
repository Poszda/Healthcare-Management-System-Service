import { Component, Inject } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';

@Component({
  selector: 'app-edit-profile-form',
  templateUrl: './edit-profile-form.component.html',
  styleUrls: ['./edit-profile-form.component.css']
})
export class EditProfileFormComponent{
  
  constructor(public dialogRef: MatDialogRef<EditProfileFormComponent>,
    @Inject(MAT_DIALOG_DATA) public data: { university: string | undefined, description: string | undefined }) {
  }

  form: FormGroup = new FormGroup({
    university: new FormControl(this.data.university, Validators.required),
    description: new FormControl(this.data.description, Validators.required),
  });

  edit() {
    this.dialogRef.close(this.form.getRawValue())
  }
}
