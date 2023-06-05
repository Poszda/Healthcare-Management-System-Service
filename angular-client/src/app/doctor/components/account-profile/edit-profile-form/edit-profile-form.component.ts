import { Component } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-edit-profile-form',
  templateUrl: './edit-profile-form.component.html',
  styleUrls: ['./edit-profile-form.component.css']
})
export class EditProfileFormComponent {
  form: FormGroup = new FormGroup({
    university: new FormControl('', Validators.required),
    description: new FormControl('', Validators.required),
  });

  edit(){

  }
}
