import { Component, OnInit } from "@angular/core";
import { HospitalManagementService } from "../../services/hospital-management.service";
import { UserService } from "../../services/user.service";
import { MessageService } from "primeng/api";

@Component({
  selector: 'app-manage',
  templateUrl: './manage.component.html',
  styleUrls: ['./manage.component.css'],
  providers:[MessageService]
})
export class ManageComponent implements OnInit {
  checkedProceduresIds : number[] = []
  hospitalId : number = 0
  constructor(private hospitalManagementService: HospitalManagementService, private userService: UserService, private messageService : MessageService) { }
  ngOnInit(): void {
    this.hospitalId = this.userService.getHospitalFromLocalStorage().id;
    this.getCheckedProcedures();
  }

  saveHospitalProcedures(){
    this.hospitalManagementService.saveHospitalProcedures(this.hospitalId,this.checkedProceduresIds).subscribe(
      res =>{
        console.log(res)
        this.showSuccess("Available hospital's procedures were successfully changed");
      },
      err =>{
        if (err.status !== 0){ //if my errors
          this.showError(err.error);
        }
        else{
          this.showError("Something went wrong")
        }
      }
    );
    console.log(this.checkedProceduresIds)
  }

  getCheckedProcedures() {
    this.hospitalManagementService.getProceduresIdsByHospitalId(this.userService.getHospitalFromLocalStorage().id).subscribe(
      res => {
        this.checkedProceduresIds = res;
      },
      err => {
        console.log(err)
      }
    );
  }

  
  showSuccess(message : string) {
    this.messageService.add({ severity: 'success', summary: 'Success', detail: message });
  }

  showError(message : string) {
    this.messageService.add({ severity: 'error', summary: 'Error', detail: message });
  }

}
