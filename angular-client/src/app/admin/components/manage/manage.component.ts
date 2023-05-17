import { Component, OnInit } from "@angular/core";
import { HospitalManagementService } from "../../services/hospital-management.service";
import { MessageService } from "primeng/api";
import { newDoctor } from "../../models/new-doctor.model";

@Component({
  selector: 'app-manage',
  templateUrl: './manage.component.html',
  styleUrls: ['./manage.component.css'],
  providers:[MessageService]
})
export class ManageComponent implements OnInit {
  checkedProceduresIds : number[] = []
  hospitalSpecialities : any[] = []
  hospitalId : number = 0

  constructor(private hospitalManagementService: HospitalManagementService, private messageService : MessageService) { }

  ngOnInit(): void {
    this.hospitalId = this.hospitalManagementService.getHospitalFromLocalStorage().id;
    this.getCheckedProcedures();
    this.getHospitalSpecialities();
  }

  getHospitalSpecialities(){
    this.hospitalManagementService.getHospitalSpecialities(this.hospitalId).subscribe(
      res =>{
        console.log(res)
        this.hospitalSpecialities = res;
      },
      err =>{
        console.log(err)
      }
    );
  }

  saveHospitalProcedures(){
    this.hospitalManagementService.saveHospitalProcedures(this.hospitalId,this.checkedProceduresIds).subscribe(
      res =>{
        this.getHospitalSpecialities();
        this.showSuccess("Available hospital's procedures were successfully changed");
      },
      err =>{
        if (err.status !== 0){
          this.showError(err.error);
        }
        else{
          this.showError("Something went wrong")
        }
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

  onCreateDoctor(form : any){
    const newDoctor : newDoctor = {
      firstName: form.firstName,
      lastName: form.lastName,
      email:form.email,
      password: form.password,
      specialityId: form.speciality,
      programEnd: form.programEnd,
      programStart: form.programStart,
      hospitalId: this.hospitalId
    }

    this.hospitalManagementService.createDoctor(newDoctor).subscribe(
      res =>{
        console.log(res)
        this.showSuccess("Doctor created succesfully");
      },
      err =>{
        console.log(err)
        if (err.status !== 0){
          this.showError(err.error);
        }
        else{
          this.showError("Something went wrong.")
        }
      }
    )
  }

  
  showSuccess(message : string) {
    this.messageService.add({ severity: 'success', summary: 'Success', detail: message });
  }

  showError(message : string) {
    this.messageService.add({ severity: 'error', summary: 'Error', detail: message });
  }

}
