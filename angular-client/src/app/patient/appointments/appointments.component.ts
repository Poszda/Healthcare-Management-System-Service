import { NoopScrollStrategy } from '@angular/cdk/overlay';
import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { AppointmentFormComponent } from './appointment-form/appointment-form.component';

@Component({
  selector: 'app-appointments',
  templateUrl: './appointments.component.html',
  styleUrls: ['./appointments.component.css']
})
export class AppointmentsComponent implements OnInit {

  constructor(public dialog: MatDialog) { }

  ngOnInit(): void {
  }

  openForm(){
    this.dialog.open(AppointmentFormComponent, {
      width: '600px',
      //data: {name: this.name, animal: this.animal}
      panelClass: 'patient-appointment-form-dialog',
      scrollStrategy: new NoopScrollStrategy()
    });
  }

}
