import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { MessageService } from 'primeng/api';
import { PrimeNGConfig } from 'primeng/api';

@Component({
  selector: 'app-appointments',
  templateUrl: './appointments.component.html',
  styleUrls: ['./appointments.component.css'],
  providers: [MessageService]
})
export class AppointmentsComponent implements OnInit {

  form : FormGroup =  new FormGroup({
    stepOne : new FormGroup(
      {
        firstname: new FormControl(''),
        lastname: new FormControl(''),
      }
    ),
    stepTwo : new FormGroup(
      {
        firstname: new FormControl(''),
        lastname: new FormControl(''),
      }
    )
  });

  constructor(private messageService: MessageService, private primengConfig: PrimeNGConfig) { }

  ngOnInit(): void {
     this.form.valueChanges.subscribe(
      res => {
        console.log(res)
      }
     )
}

  showSuccess() {
    this.messageService.add({ severity: 'success', summary: 'Success', detail: 'Appointment registered succesfully' });
  }

  showInfo() {
    this.messageService.add({ severity: 'info', summary: 'Info', detail: 'Message Content' });
  }

  showWarn() {
    this.messageService.add({ severity: 'warn', summary: 'Warn', detail: 'Message Content' });
  }

  showError() {
    this.messageService.add({ severity: 'error', summary: 'Error', detail: 'Message Content' });
  }



}
