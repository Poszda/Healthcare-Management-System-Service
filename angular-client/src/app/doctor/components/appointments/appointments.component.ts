import { Component, OnInit } from '@angular/core';
import { AppointmentsService } from '../../services/appointments.service';
import { DoctorService } from '../../services/doctor.service';
import { DoctorAppointment, DoctorAppointmentTabelData } from '../../models/doctor-appointments.model';
import { AppointmentStatus } from '../../models/appointment-status.enum';
import * as moment from 'moment';
import { drawPoint } from 'chart.js/dist/helpers/helpers.canvas';
import { MatDialog } from '@angular/material/dialog';
import { DiagnosticFormComponent } from './diagnostic-form/diagnostic-form.component';
import { newDiagnostic } from '../../models/new-diagnostic.model';

@Component({
  selector: 'app-appointments',
  templateUrl: './appointments.component.html',
  styleUrls: ['./appointments.component.css'],
})
export class AppointmentsComponent implements OnInit {
  doctorId : number = 0;
  appointmentsData : DoctorAppointmentTabelData[] = []
  constructor(private appointmentsService : AppointmentsService, private doctorService : DoctorService,public dialog: MatDialog){}

  ngOnInit(): void {
    this.doctorId = this.doctorService.getDoctorFromLocalStorage().id
    this.getDoctorAppointments();
  }

  getDoctorAppointments(){
    this.appointmentsService.getDoctorAppointments(this.doctorId).subscribe(
      (res : DoctorAppointment[]) =>{
        this.appointmentsData = this.transformAppointmentData(res);
      },
      err =>{
        console.log(err)
      }
    )
  }

  transformAppointmentData(res : DoctorAppointment[]) : DoctorAppointmentTabelData[]{
    const result = res.map(el => {
     const appointment : DoctorAppointmentTabelData ={
     id: el.id,
     date : el.date,
     time: this.getInterval(el.time,el.duration),
     procedureName: el.procedureName,
     patientId: el.patientId,
     name: el.firstName + ' ' + el.lastName,
     phone: el.phone,
     age: el.age,
     status : el.status,
     profileImage:el.profileImage 
     }
     return appointment
    })

    return result;
  }

  getInterval(time : string, duration : number){
    return `${moment(time, 'HH:mm').format('HH:mm')} - ${moment(time, 'HH:mm').add(duration,'minutes').format('HH:mm')}`
  }

  openForm(appointment : DoctorAppointmentTabelData){
    this.dialog.open(DiagnosticFormComponent, {
      panelClass: 'diagnostic-form-dialog',
      maxHeight:'80vh',
      width:'500px',
      data : appointment
    }).afterClosed().subscribe(
      (diagnostic) =>{
        if(diagnostic){
          diagnostic.appointmentId = appointment.id 
          this.createDiagnostic(diagnostic);
        }
      }
    )
  }

  createDiagnostic(diagnostic : newDiagnostic){
    this.appointmentsService.createDiagnostic(diagnostic).subscribe(
      res =>{
        this.getDoctorAppointments();
      },
      err =>{
        console.log(err)
      }
    )
  }


}
