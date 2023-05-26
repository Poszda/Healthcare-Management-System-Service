import { BreakpointObserver } from '@angular/cdk/layout';
import { Component, EventEmitter, Input, OnChanges, OnDestroy, OnInit, Output, SimpleChanges, ViewChild } from '@angular/core';
import { Table } from 'primeng/table';
import { Subscription } from 'rxjs';
import { DoctorAppointment, DoctorAppointmentTabelData } from 'src/app/doctor/models/doctor-appointments.model';

@Component({
  selector: 'app-appointments-table',
  templateUrl: './appointments-table.component.html',
  styleUrls: ['./appointments-table.component.css']
})
export class AppointmentsTableComponent implements OnChanges,OnInit,OnDestroy {
  @Input() data : DoctorAppointmentTabelData[] = []
  @Output() addDiagnostic : EventEmitter<number> = new EventEmitter();
  difValue : number = 100;
  subscription : Subscription = new Subscription();

  @ViewChild('dt') dt: Table | undefined;

  constructor(private breakpointObserver: BreakpointObserver){
  }

  ngOnChanges(changes: SimpleChanges): void {
  }

  ngOnInit(): void {
    this.observeScreenSize();
  }

  ngOnDestroy(): void {
    this.subscription.unsubscribe();
  }

  applyFilterGlobal($event : any, stringVal : any) {
    this.dt!.filterGlobal(($event.target as HTMLInputElement).value, stringVal);
  }

  openFormDialog(appointmentId : number){
    this.addDiagnostic.emit(appointmentId);
  }

  observeScreenSize(){
    this.subscription.add(
    this.breakpointObserver.observe('(max-width: 599px)').subscribe(
      result =>{
          if (result.matches) {
            // Code to be executed when the viewport matches the Small breakpoint
            console.log('Viewport matches Small breakpoint');
            //this.formula = 'calc(100vh - 500px)'
          } else {
            // Code to be executed when the viewport does not match the Small breakpoint
          }
      }));
  }


}
