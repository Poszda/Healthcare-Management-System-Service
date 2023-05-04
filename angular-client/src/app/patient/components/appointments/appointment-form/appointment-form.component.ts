import { AfterViewInit, Component, OnInit, ViewChild } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { MatStepper } from '@angular/material/stepper';
import { Inject } from '@angular/core';
import { AppointmentsService } from '../../../services/appointments.service';
import { Doctor } from 'src/app/core/models/doctor.model';
import { SpecialitiesService } from '../../../services/specialities.service';
import { HospitalsService } from '../../../services/hospitals.service';
import { forkJoin } from 'rxjs';

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
    speciality: new FormControl(null, Validators.required),
    procedure: new FormControl({ value: null, disabled: true }, Validators.required),
  });

  formOptional: FormGroup = new FormGroup({
    doctors: new FormControl({ value: [], disabled: true }),
    hospitals: new FormControl({ value: [], disabled: true }),
  })

  countiesOptions: any = []
  specialityOptions: any = []
  procedureOptions: any = []
  filteredProcedureOptions: any = []
  hospitalsOptions: any[] = []
  doctorsOptions: any = []
  filteredDoctorsOptions: any = []

  loading: boolean = false;
  loadingSpinner: boolean = false


  constructor(public dialogRef: MatDialogRef<AppointmentFormComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any,
    private appointmentService: AppointmentsService,
    private specialitiesService: SpecialitiesService,
    private hospitalsService: HospitalsService
  ) { }

  endDateGreaterThanStartDateValidator(control: FormControl): { [s: string]: boolean } | null{
    const startDate = control.root.get('startDate');
    const endDate = control.value;
    if (startDate && endDate && new Date(startDate.value) > new Date(endDate)) {
      return { endDateGreaterThanStartDate: true };
    }
    return null;
  }


  ngOnInit(): void {
    this.subscribeToSpecialityChange();
    this.subscribeToHospitalChange();
    this.getMandatoryOptions();
  }

  getMandatoryOptions() {
    this.loadingSpinner = true;
    const calls = [
      this.hospitalsService.getAllHospitalsCounties(),
      this.specialitiesService.getSpecialitiesWithProcedures()
    ]
    forkJoin(calls)
    .subscribe(
      (res) => {
        setTimeout(() => {
        console.log(res)
        this.countiesOptions = res[0].map((el:any) => ({name:el}));
        this.specialityOptions = res[1];
        this.procedureOptions = this.extractProceduresFromSpecialitiesArray(res[1])
        this.filteredProcedureOptions = [];
        this.loadingSpinner = false;
        }, 500);
      },
      err => {
        console.log(err);
      }
    )
  }

  getOptionalOptions() {
    this.loading = true;
    this.formOptional.disable({ emitEvent: false });
    this.appointmentService.getFormOptionalOptions(this.formMandatory.get('county')?.value, this.formMandatory.get('speciality')?.value).subscribe(
      (res: any[]) => {
        setTimeout(() => {
          console.log(res)
          this.hospitalsOptions =[...res];
          this.doctorsOptions = this.extractDoctorsFromHospitalArray(res);
          this.filteredDoctorsOptions = [...this.doctorsOptions]
          this.formOptional.enable({ emitEvent: false });
          this.loading = false;
        }, 500);
      },
      err => {
        console.log(err)
      }
    )
  }

  extractDoctorsFromHospitalArray(array: any) {
    return array
      .reduce((accumulator: Doctor[], hospital: any) => [...accumulator, ...hospital.doctors], [])
      .map((el: any) => ({ ...el, name: el.user.firstName + " " + el.user.lastName }))
  }

  getAvailableAppointments() {
    const manadatoryForm = this.formMandatory.getRawValue();
    const optionalForm = this.formOptional.getRawValue();
    let doctorsIds;
    if (optionalForm.hospitals.length > 0 && optionalForm.doctors.length === 0){ 
      doctorsIds = this.doctorsOptions.filter((el : any) => optionalForm.hospitals.includes(el.hospitalId)).map((el:any) => el.id)
    }
    else{
      doctorsIds = optionalForm.doctors
    }
    const req = {
      startDate:manadatoryForm.startDate,
      endDate:manadatoryForm.endDate,
      doctorsIds:doctorsIds
    }
    req.startDate.setHours(0,0,0,0)
    req.endDate.setHours(0,0,0,0)
    console.log(req)
    req.startDate = req.startDate.toISOString();
    req.endDate = req.endDate.toISOString();


    this.appointmentService.getAvailableAppointments(req).subscribe(
      res =>{
        console.log(res)
      },
      err =>{
        console.log(err)
      }
    )
  }


  extractProceduresFromSpecialitiesArray(array : any){
    return array
    .reduce((accumulator : any,speciality : any) => [...accumulator, ...speciality.procedures],[])
  }

  subscribeToSpecialityChange() {
    this.formMandatory.get('speciality')?.valueChanges.subscribe(
      (res) => {
        if (this.formMandatory.get('speciality')?.valid) {
          this.filterProceduresBySpeciality(this.formMandatory.get('speciality')?.value);
          if (this.formMandatory.get('procedure')?.disabled)
            this.formMandatory.get('procedure')?.enable({ emitEvent: false })
        }
        else {
          if (this.formMandatory.get('speciality')?.value === null) {
            this.formMandatory.get('procedure')?.reset();
          }
          this.formMandatory.get('procedure')?.disable({ emitEvent: false })
        }
      }
    )
  }

  subscribeToHospitalChange() {
    this.formOptional.get('hospitals')?.valueChanges.subscribe(
      (res) => {
        this.formOptional.get('doctors')?.setValue([]);
        const hospitalIds = this.formOptional.get('hospitals')?.value;
        if (hospitalIds.length > 0) {
          const selectedHospitals = this.hospitalsOptions.filter(el => hospitalIds.includes(el.id));
          this.filteredDoctorsOptions = this.extractDoctorsFromHospitalArray(selectedHospitals)
        }
        else {
          this.filteredDoctorsOptions = this.doctorsOptions
        }
      }
    )
  }

  filterProceduresBySpeciality(selectedSpeciality?: number) {
    if (!selectedSpeciality) this.filteredProcedureOptions = [...this.procedureOptions]
    this.filteredProcedureOptions = this.procedureOptions.filter((el: any) => el.specialityId === selectedSpeciality)
  }

  saveAppointment() {
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
