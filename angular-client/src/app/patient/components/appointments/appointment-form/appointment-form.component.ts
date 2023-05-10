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
import * as moment from 'moment';
import { UserService } from 'src/app/patient/services/user.service';
import { DoctorsAvailableHours } from 'src/app/patient/models/doctor-available-hours.model';
import { AppointmentSuggestion } from 'src/app/patient/models/appointment-suggestion.model';

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

  appointmentSuggestions : AppointmentSuggestion[] = [];


  constructor(public dialogRef: MatDialogRef<AppointmentFormComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any,
    private appointmentService: AppointmentsService,
    private specialitiesService: SpecialitiesService,
    private hospitalsService: HospitalsService,
    private userService : UserService
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

  getDoctorsAvailableHours() {
    const manadatoryForm = this.formMandatory.getRawValue();
    const optionalForm = this.formOptional.getRawValue();
    let doctorsIds;
    if (optionalForm.hospitals.length > 0 && optionalForm.doctors.length === 0){ 
      doctorsIds = this.doctorsOptions.filter((el : any) => optionalForm.hospitals.includes(el.hospitalId)).map((el:any) => el.id)
    }
    else if(optionalForm.hospitals.length === 0 && optionalForm.doctors.length === 0){
      doctorsIds = this.doctorsOptions.map((el:any) => el.id)
    }
    else{
      doctorsIds = optionalForm.doctors
    }
    const req = {
      startDate: moment(manadatoryForm.startDate).format(),
      endDate:moment(manadatoryForm.endDate).hour(23).minute(59).format(),
      doctorsIds:doctorsIds,
      procedureId:manadatoryForm.procedure
    }

    // calling apis
    this.loading = true;
    const calls = [
      this.appointmentService.getDoctorsAvailableHours(req),
      this.userService.getDoctorsWithHospitalsById(doctorsIds)
    ]
    forkJoin(calls)
    .subscribe(
      ([res1,res2]) => {
        setTimeout(() => {
        this.appointmentSuggestions =  this.sortAppointmentsByDate(this.createAppointmentSuggestions(res1,res2))
        console.log(this.appointmentSuggestions);
        this.loading = false;
        }, 500);
      },
      err => {
        console.log(err);
      }
    )
  }

  createAppointmentSuggestions(doctorsAvailableHours : DoctorsAvailableHours[], doctors : any[]): AppointmentSuggestion[]{
    const list : AppointmentSuggestion[] = [];
    doctorsAvailableHours.forEach(el => {
      const doctorExtended = doctors.find(de => de.id === el.doctorId) // shoul be always found
      el.dates.forEach(d => {
        const obj : AppointmentSuggestion = {
          doctorName: doctorExtended.user.firstName + " " + doctorExtended.user.lastName,
          doctorSpeciality: "Speciality 1",
          hospitalName: doctorExtended.hospital.name,
          date: d.date,
          hours: d.hours
        }
        list.push(obj)
      });
    });
    return list;
  }

  sortAppointmentsByDate(appointments: AppointmentSuggestion[]): AppointmentSuggestion[] {
    return appointments.sort((a, b) => {
      const dateA = new Date(a.date);
      const dateB = new Date(b.date);
      if (dateA < dateB) {
        return -1;
      }
      if (dateA > dateB) {
        return 1;
      }
      return 0;
    });
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

  onSuggestionSelection(){
    
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
