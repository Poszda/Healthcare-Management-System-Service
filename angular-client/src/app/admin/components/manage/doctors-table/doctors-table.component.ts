import { Component, EventEmitter, Input, OnInit, Output, ViewChild } from '@angular/core';
import { Table } from 'primeng/table';
import { StatisticsService } from 'src/app/admin/services/statistics.service';

@Component({
  selector: 'app-doctors-table',
  templateUrl: './doctors-table.component.html',
  styleUrls: ['./doctors-table.component.css']
})
export class DoctorsTableComponent implements OnInit{
  @Input() data : any = []
  @Output() addDoctor : EventEmitter<any> = new EventEmitter();
  @Output() deleteDoctor : EventEmitter<number> = new EventEmitter();

  @ViewChild('dt') dt: Table | undefined;

  constructor(){}

  applyFilterGlobal($event : any, stringVal : any) {
    this.dt!.filterGlobal(($event.target as HTMLInputElement).value, stringVal);
  }

  ngOnInit(): void {
  }

  openFormDialog(){
    this.addDoctor.emit();
  }

  deleteDoctorData(doctorId : any){
    this.deleteDoctor.emit(doctorId)
  }
}
