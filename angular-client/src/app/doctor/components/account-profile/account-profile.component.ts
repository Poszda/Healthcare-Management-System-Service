import { Component,OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { AlertService } from 'src/app/core/services/alert.service';
import { AuthService } from 'src/app/core/services/auth.service';
import { EditProfileFormComponent } from './edit-profile-form/edit-profile-form.component';
import { ConfirmationDialogComponent } from 'src/app/shared/components/confirmation-dialog/confirmation-dialog.component';

@Component({
  selector: 'app-account-profile',
  templateUrl: './account-profile.component.html',
  styleUrls: ['./account-profile.component.css']
})
export class AccountProfileComponent implements OnInit {
  constructor(private authService : AuthService, private dialog : MatDialog, private alertService : AlertService){}

  ngOnInit(): void {
  }

  logOut(){
    this.dialog.open(ConfirmationDialogComponent,{
      maxHeight:'80vh',
      width:'600px',
      data : {message : "Are you sure you want to log out?"}
    }).afterClosed().subscribe(
      succes =>{
        if(succes === true){
          this.authService.logout();
        }
      }
    )

  }

  editProfile(){
    this.dialog.open(EditProfileFormComponent, {
      panelClass: 'patient-appointment-form-dialog',
      maxHeight:'80vh',
      width:'600px',
      //scrollStrategy: new NoopScrollStrategy()
    }).afterClosed().subscribe(
      succes =>{
        if(succes === true){
          this.alertService.showSuccess('Appointment registered succesfully');
        }
        else if(succes === false){
          this.alertService.showError('We could not register your appointment');
        }
      }
    )
  }
}
