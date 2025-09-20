import { Component, OnInit, ViewChild } from "@angular/core";
import { HospitalManagementService } from "../../services/hospital-management.service";
import { DoctorCreationFormComponent } from "./doctor-creation-form/doctor-creation-form.component";
import { MatDialog } from "@angular/material/dialog";
import { AlertService } from "src/app/core/services/alert.service";

@Component({
  selector: 'app-manage',
  templateUrl: './manage.component.html',
  styleUrls: ['./manage.component.css'],
})
export class ManageComponent implements OnInit {
  checkedProceduresIds : number[] = []
  hospitalSpecialities : any[] = []
  doctorsTableData : any[] = []
  hospitalId : number = 0

  constructor(private hospitalManagementService: HospitalManagementService,public dialog: MatDialog, private alertService : AlertService) { }

  ngOnInit(): void {
    this.hospitalId = this.hospitalManagementService.getHospitalFromLocalStorage().id;
    this.getCheckedProcedures();
    this.getHospitalSpecialities();
    this.getDoctorsTableData();
  }

  getDoctorsTableData(){
    this.hospitalManagementService.getDoctorsTableData(this.hospitalId).subscribe(
      res =>{
        this.doctorsTableData = res;
      },
      err =>{
        console.log(err)
      }
    )
  }

  getHospitalSpecialities(){
    this.hospitalManagementService.getHospitalSpecialities(this.hospitalId).subscribe(
      res =>{
        this.hospitalSpecialities = res;
      },
      err =>{
        console.log(err)
      }
    );
  }

  getCheckedProcedures() {
    this.hospitalManagementService.getProceduresIdsByHospitalId(this.hospitalManagementService.getHospitalFromLocalStorage().id).subscribe(
      res => {
        this.checkedProceduresIds = res;
      },
      err => {
        console.log(err)
      }
    );
  }

  openForm(){
    this.dialog.open(DoctorCreationFormComponent, {
      panelClass: 'doctor-creation-form-dialog',
      maxHeight:'80vh',
      width:'500px',
      data : {specialities: this.hospitalSpecialities, hospitalId:this.hospitalId}
      //scrollStrategy: new NoopScrollStrategy()
    }).afterClosed().subscribe(
      doctorRaw =>{
        if(typeof doctorRaw === "object" && doctorRaw){
          this.getDoctorsTableData();
        }
      }
    )
  }

  deleteDoctor(doctorId : number){
    this.hospitalManagementService.deleteDoctor(doctorId).subscribe(
      res =>{
        this.getDoctorsTableData();
      },
      err =>{
        console.log(err)
      }
    )
  }

  saveHospitalProcedures(){
    this.hospitalManagementService.saveHospitalProcedures(this.hospitalId,this.checkedProceduresIds).subscribe(
      res =>{
        this.getHospitalSpecialities();
        this.alertService.showSuccess("Available hospital's procedures were successfully changed");
      },
      err =>{
        this.alertService.showError('Failed to save hospital procedures');
      }
    );
  }
}
