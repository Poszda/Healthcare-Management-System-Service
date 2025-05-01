import { Component, Inject } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';

@Component({
  selector: 'app-edit-profile-form',
  templateUrl: './edit-profile-form.component.html',
  styleUrls: ['./edit-profile-form.component.css']
})
export class EditProfileFormComponent{

  maxlength = 2000;
  constructor(public dialogRef: MatDialogRef<EditProfileFormComponent>,
    @Inject(MAT_DIALOG_DATA) public data: { university: string | undefined, description: string | undefined }) {
  }

  form: FormGroup = new FormGroup({
    university: new FormControl(this.data.university || ''),
    description: new FormControl(this.data.description|| ''),
    profileImage: new FormControl(null)
  });

  edit() {
    this.form.get('description')
    this.dialogRef.close(this.form.getRawValue())
  }

  onFileUpload($data : File){
    this.form.get('profileImage')?.setValue($data);
  }

}
