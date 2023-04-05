import { AfterViewInit, Component, OnInit, ViewChild } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { MatStepper } from '@angular/material/stepper';
import {Inject} from '@angular/core';

@Component({
  selector: 'app-appointment-form',
  templateUrl: './appointment-form.component.html',
  styleUrls: ['./appointment-form.component.css']
})
export class AppointmentFormComponent implements OnInit {
  formMandatory: FormGroup = new FormGroup({
      startDate: new FormControl('', Validators.required),
      endDate: new FormControl('', Validators.required),
      county: new FormControl([], Validators.required),
      speciality: new FormControl('', Validators.required),
      procedure: new FormControl({ value: '', disabled: true }, Validators.required),
  });

  formOptional :FormGroup = new FormGroup({
    doctors: new FormControl({ value: [], disabled: true }),
    hospitals: new FormControl({ value: [], disabled: true }),
  })

  countiesOptions: any = []
  specialityOptions: any = []
  procedureOptions: any = []
  filteredProcedureOptions: any = []
  hospitalsOptions: any = []
  doctorsOptions: any = []

  loading : boolean = false;
  loadingSpinner : boolean = false


  constructor(public dialogRef: MatDialogRef<AppointmentFormComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any) { }


  ngOnInit(): void {
    console.log(this.formMandatory,this.formOptional)
    this.getMandatoryOptions();
    this.subscribeToSpecialityChange();
  }
  
  getMandatoryOptions() {
    this.loadingSpinner = true;
    this.countiesOptions = [
      { name: 'New York', code: 'NY' },
      { name: 'Rome', code: 'RM' },
      { name: 'London', code: 'LDN' },
      { name: 'Istanbul', code: 'IST' },
      { name: 'Paris', code: 'PRS' }
    ];
    setTimeout(() => {
      this.specialityOptions = [
        { "name": "Cardiologie", "code": 1 },
        { "name": "Dentologie", "code": 2 },
        { "name": "Organologie", "code": 3 },
        { "name": "Psihiatrie", "code": 4 },
      ]
      this.procedureOptions = [
        { "name": "Returnare Valva", "code": 1, "specialityId": 1 },
        { "name": "Extrac de suc", "code": 2,"specialityId": 1 },
        { "name": "Operatie deviatie de sept", "code": 3,"specialityId": 2 },
        { "name": "Psihopiloba", "code": 4,"specialityId": 3 },
        { "name": "Pronostic anafilactic", "code": 4,"specialityId": 4 },
      ]
      this.loadingSpinner = false;
    }, 2000);
  }

/*   getOptionalOptions() {
    this.loading = true;
    this.form.get('mandatory')?.disable({emitEvent:false});
    setTimeout(() => {
      this.hospitalsOptions = [
        { name: 'Iulius Town Hospital', code: 1 },
        { name: 'George Dumitru Clinic', code: 2 },
        { name: 'Senna Days', code: 3 },
        { name: 'Regina Maria', code: 4 },
        { name: 'Hpital Sopital', code: 5 },
      ]
      this.doctorsOptions = [
        { name: "Andrei Popescu", code: 1 },
        { name: "Ionescu Popescu", code: 2 },
        { name: "George Gopescu", code: 3 },
        { name: "Pandrei Copescu", code: 4 }
      ]
      this.loading = false;
      this.form.get('mandatory')?.enable({emitEvent:false});
    }, 1000);
  }
  */

  subscribeToSpecialityChange() {
    this.formMandatory.get('speciality')?.valueChanges.subscribe(
      (res) => {
        console.log('called speciality changes')
        if (this.formMandatory.get('speciality')?.valid) {
          this.filterProceduresBySpeciality(this.formMandatory.get('speciality')?.value);
          if (this.formMandatory.get('procedure')?.disabled)
            this.formMandatory.get('procedure')?.enable({emitEvent:false})
        }
        else {
          if(this.formMandatory.get('speciality')?.value === null){
            this.formMandatory.get('procedure')?.reset();
          }
          this.formMandatory.get('procedure')?.disable({emitEvent:false})
        }
      }
    )
  }

  filterProceduresBySpeciality(selectedSpeciality? : number){
    if(!selectedSpeciality) this.filteredProcedureOptions = [...this.procedureOptions]
    this.filteredProcedureOptions = this.procedureOptions.filter((el : any) => el.specialityId === selectedSpeciality)
  }

  saveAppointment(){
    this.loading = true;
    //make save call
    //disable buttons and dialogs
    // close dialog 
    setTimeout(() => {
      this.loading = false
      this.dialogRef.close(true);
    }, 2000);
  }

}
