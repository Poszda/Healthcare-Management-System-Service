import { BreakpointObserver } from '@angular/cdk/layout';
import { Component, EventEmitter, Input, OnChanges, OnDestroy, OnInit, Output, SimpleChanges, ViewChild } from '@angular/core';
import * as moment from 'moment';
import { Table } from 'primeng/table';
import { Subscription } from 'rxjs';
import { AppointmentStatus } from 'src/app/doctor/models/appointment-status.enum';
import { DoctorAppointment, DoctorAppointmentTabelData } from 'src/app/doctor/models/doctor-appointments.model';

@Component({
  selector: 'app-appointments-table',
  templateUrl: './appointments-table.component.html',
  styleUrls: ['./appointments-table.component.css']
})
export class AppointmentsTableComponent implements OnChanges, OnInit, OnDestroy {
  @Input() data: DoctorAppointmentTabelData[] = []
  @Output() addDiagnostic: EventEmitter<DoctorAppointmentTabelData> = new EventEmitter();
  @ViewChild('dt') dt: Table | undefined;
  maxDate: Date = moment().add(90, 'days').toDate();
  minDate: Date = moment().subtract(90, 'days').toDate();

  dataFiltered: DoctorAppointmentTabelData[] = []
  periodOptions: string[] = [];
  statusOptions: string[] = ["All", AppointmentStatus.IN_PROGRESS, AppointmentStatus.REVIEWED, AppointmentStatus.UPCOMING]

  filter: { status: string, period: null | [Date | null, Date | null] } = {
    status: "All",
    period: [moment().startOf('day').subtract(30, 'days').toDate(), moment().startOf('day').add(30, 'days').toDate()] //null to the second one
  }


  subscription: Subscription = new Subscription();
  appointmentStatus = AppointmentStatus;
  difValue: number = 100;


  constructor(private breakpointObserver: BreakpointObserver) {
  }

  ngOnChanges(changes: SimpleChanges): void {
    if (changes['data']) {
      this.filterAppointments();
    }
  }

  ngOnInit(): void {
    this.observeScreenSize();
  }

  ngOnDestroy(): void {
    this.subscription.unsubscribe();
  }

  filterAppointments() {
    this.dataFiltered = this.data.filter(el =>
      el.status === this.filter.status || this.filter.status === "All" &&
      this.filterByDate(el.date)
    )
  }

  filterByDate(date: string) {
    const x = moment(date);
    const startDate = this.filter.period && this.filter.period[0] ? moment(this.filter.period[0]) : null
    const endDate = this.filter.period && this.filter.period[1] ? moment(this.filter.period[1]) : null
    if (startDate && endDate && (x.isBetween(startDate, endDate))) return true
    if (startDate && startDate.isSame(x, 'day')) return true;
    if (endDate && endDate.isSame(x, 'day')) return true;
    return false;
  }

  applyFilterGlobal($event: any, stringVal: any) {
    this.dt!.filterGlobal(($event.target as HTMLInputElement).value, stringVal);
  }

  openFormDialog(appointmentId: DoctorAppointmentTabelData) {
    this.addDiagnostic.emit(appointmentId);
  }

  observeScreenSize() {
      this.subscription.add(
      this.breakpointObserver.observe([
        '(min-width: 1024px)',
        '(min-width: 768px) and (max-width: 1024px)',
        '(min-width: 480px) and (max-width: 768px)',
        '(min-width: 0px) and (max-width: 480px)'
      ]).subscribe(
        result => {
          if (result.matches) {
            if(result.breakpoints['(min-width: 1024px)']) this.difValue = 100;
            else if(result.breakpoints['(min-width: 768px) and (max-width: 1024px)']) this.difValue = 130;
            else if(result.breakpoints['(min-width: 480px) and (max-width: 768px)']) this.difValue = 222;
            else if(result.breakpoints['(min-width: 0px) and (max-width: 480px)']) this.difValue = 302;
          }
        }));
  }


}
