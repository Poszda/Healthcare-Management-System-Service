import { Component, OnDestroy, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { PrescriptionDialogComponent } from './prescription-dialog/prescription-dialog.component';
import { AppointmentsService } from '../../services/appointments.service';
import { UserService } from '../../services/user.service';
import { DiagnosticExtended } from '../../models/diagnostic-extended.model';

@Component({
  selector: 'app-prescriptions',
  templateUrl: './prescriptions.component.html',
  styleUrls: ['./prescriptions.component.css']
})
export class PrescriptionsComponent implements OnInit,OnDestroy{

  diagnostics : DiagnosticExtended[] = [];
  constructor(public dialog: MatDialog, private appointmentsService : AppointmentsService,private userService : UserService){}

  ngOnInit(): void {
    this.getDiagnostics();
  }

  ngOnDestroy(): void {
    console.log('destroyed')
  }

  openPrescriptionDialog(diagnostic : DiagnosticExtended ){
    this.dialog.open(PrescriptionDialogComponent, {
      panelClass: 'prescription-dialog',
      maxHeight:'80vh',
      width:'900px',
      data : diagnostic
    })
  }

  getDiagnostics(){
    this.appointmentsService.getDiagnostics(this.userService.getPatientIdFromLocalStorage()!).subscribe(
      res =>{
        this.diagnostics = res.sort((a : any, b : any) =>  new Date(b.createdAt).getTime() - new Date(a.createdAt).getTime());
      },
      err =>{
        console.log(err)
      }
    )
  }

}
