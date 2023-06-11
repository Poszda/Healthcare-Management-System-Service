import { Component, OnInit } from '@angular/core';
import { AppointmentsService } from '../../services/appointments.service';
import { AppointmentNext } from 'src/app/shared/models/appointment-next.model';
import { UserService } from '../../services/user.service';
import { Patient } from 'src/app/core/models/patient.model';
import { DiagnosticExtended } from '../../models/diagnostic-extended.model';
import * as moment from 'moment';
import { MatDialog } from '@angular/material/dialog';
import { PrescriptionDialogComponent } from '../prescriptions/prescription-dialog/prescription-dialog.component';
import { SpecialitiesService } from '../../services/specialities.service';


@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {
  patientId: number = 0;
  todayNextAppointments : AppointmentNext[] = []
  patient : Patient | undefined;
  diagnostics : DiagnosticExtended[] = [];
  constructor(private appointmentsService : AppointmentsService, private userService : UserService, private specialitiesService : SpecialitiesService, private dialog : MatDialog) { }

  ngOnInit(): void {
    this.patientId = this.userService.getPatientIdFromLocalStorage()!;
    this.getNextAppointments();
    this.getPatient();
    this.getDiagnostics();
  }


  getDiagnostics(){
    this.appointmentsService.getDiagnostics(this.patientId).subscribe(
      res => {
        this.diagnostics = res.sort(this.compareDates).slice(0,2)
        console.log(res)
      },
      err => {
        console.log(err)
      }
    )
  }

  compareDates(a : DiagnosticExtended,b : DiagnosticExtended){
    if(moment(a.createdAt).isAfter(moment(b.createdAt))) return 1
    return -1;
  }

  getPatient(){
    this.userService.getPatient(this.patientId).subscribe(
      res => {
        this.patient = res
      },
      err => {
        console.log(err)
      }
    )
  }

  getNextAppointments(){
    this.appointmentsService.getNextAppointments(this.patientId).subscribe(
      res => {
        this.todayNextAppointments = res.slice(0,10)
      },
      err => {
        console.log(err)
      }
    )
  }

  openDiagnosticDialog(diagnostic : DiagnosticExtended){
    this.dialog.open(PrescriptionDialogComponent, {
      panelClass: 'prescription-dialog',
      maxHeight:'80vh',
      width:'900px',
      data : diagnostic
    })
  }
}
