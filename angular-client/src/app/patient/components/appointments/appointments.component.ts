import { NoopScrollStrategy } from '@angular/cdk/overlay';
import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { AppointmentFormComponent } from './appointment-form/appointment-form.component';
import { MessageService } from 'primeng/api';
import { AppointmentsService } from '../../services/appointments.service';
import { UserService } from '../../services/user.service';
import { AppointmentCard } from '../../models/appointment-card.model';
import * as moment from 'moment';

@Component({
  selector: 'app-appointments',
  templateUrl: './appointments.component.html',
  styleUrls: ['./appointments.component.css'],
  providers: [MessageService]
})
export class AppointmentsComponent implements OnInit {

  futureAppointments : AppointmentCard[] = []
  pastAppointments : AppointmentCard[] = []

  constructor(public dialog: MatDialog, 
    private messageService: MessageService,
    private appointmentsService : AppointmentsService,
    private userService : UserService) { }

  ngOnInit(): void {
    this.getAppointments();

  }

  getAppointments(){
    this.appointmentsService.getAppointmentsCards(this.userService.getPatientIdFromLocalStorage()!).subscribe(
      (res : AppointmentCard[]) =>{
        this.futureAppointments = res.filter(el => moment(el.dateTime).isAfter(moment())).sort(this.compareDates).reverse();
        this.pastAppointments = res.filter(el => moment(el.dateTime).isBefore(moment())).sort().reverse();
      },
      err =>{
        console.log(err)
      }
    )  
  }

  onDeleteAppointment(id : number){
    this.appointmentsService.deleteAppointment(id).subscribe(
      res =>{
        this.getAppointments();
        this.showSuccess("Appointment deleted succesfully")
      },
      err =>{
        console.log(err)
        this.showError("Error when deleting appointment");
      }
    )
  }

  compareDates(a : AppointmentCard,b : AppointmentCard){
    if(moment(a.dateTime).isAfter(moment(b.dateTime))) return 1
    return -1;
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
        if(succes === true){
          this.showSuccess('Appointment registered succesfully');
          this.getAppointments();
        }
        else if(succes === false){
          this.showError('We could not register your appointment');
        }
      }
    )
  }

  showSuccess(message : string) {
    this.messageService.add({ severity: 'success', summary: 'Success', detail:message });
  }

  showError(message : string) {
    this.messageService.add({ severity: 'error', summary: 'Something went wrong', detail: message});
  }

}
