import { NoopScrollStrategy } from '@angular/cdk/overlay';
import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { AppointmentFormComponent } from './appointment-form/appointment-form.component';
import { MessageService } from 'primeng/api';

@Component({
  selector: 'app-appointments',
  templateUrl: './appointments.component.html',
  styleUrls: ['./appointments.component.css'],
  providers: [MessageService]
})
export class AppointmentsComponent implements OnInit {

  constructor(public dialog: MatDialog, private messageService: MessageService) { }

  ngOnInit(): void {
  }

  openForm(){
    this.dialog.open(AppointmentFormComponent, {
      panelClass: 'patient-appointment-form-dialog',
      maxWidth:'1000px',
      maxHeight:'80vh',
      width:'80vw',
      //scrollStrategy: new NoopScrollStrategy()
    }).afterClosed().subscribe(
      succes =>{
        if(succes){
          this.showSuccess();
        }
        else{
          this.showError();
        }
      }
    )
  }

  showSuccess() {
    this.messageService.add({ severity: 'success', summary: 'Success', detail: 'Appointment registered succesfully' });
  }

  showError() {
    this.messageService.add({ severity: 'error', summary: 'Something went wrong', detail: 'We could not register your appointment' });
  }

}
