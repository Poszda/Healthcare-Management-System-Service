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
import { DoctorsAvailableHours } from 'src/app/patient/models/doctor-available-hours.model';
import { AppointmentSuggestion } from 'src/app/patient/models/appointment-suggestion.model';
import { AppointmentSummary } from 'src/app/patient/models/appointment-summary.model';
import { NewAppointment } from 'src/app/patient/models/new-appointment.model';
import { UserService } from 'src/app/patient/services/user.service';
import { DoctorSuggestionInfo } from 'src/app/patient/models/doctor-suggestion-info.model';

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
  sugestionSelection : any
  appointmentSummary : AppointmentSummary | undefined;

  countiesOptions: any = []
  specialityOptions: any = []
  procedureOptions: any = []
  filteredProcedureOptions: any = []
  hospitalsOptions: any[] = []
  doctorsOptions: any = []
  filteredDoctorsOptions: any = []
  selectedDoctorsInfo : DoctorSuggestionInfo[] = []
  appointmentSuggestions : AppointmentSuggestion[] = [];

  loading: boolean = false;
  loadingSpinner: boolean = false

  constructor(public dialogRef: MatDialogRef<AppointmentFormComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any,
    private appointmentService: AppointmentsService,
    private specialitiesService: SpecialitiesService,
    private hospitalsService: HospitalsService,
    private userService : UserService
  ) { }


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
    this.appointmentService.getAppointmentOptionals(this.formMandatory.get('county')?.value, this.formMandatory.get('procedure')?.value).subscribe(
      (res: any) => {
        //set timeout for short loading effect
        setTimeout(() => {
          this.hospitalsOptions = res.hospitals;
          this.doctorsOptions = res.doctors.map((el: any) => ({...el, name: el.user.firstName + " " + el.user.lastName}));
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
      startDate: moment(manadatoryForm.startDate).format("YYYY-MM-DDTHH:mm:ss"),
      endDate: moment(manadatoryForm.endDate).hour(23).minute(59).format("YYYY-MM-DDTHH:mm:ss"),
      doctorsIds:doctorsIds,
      procedureId:manadatoryForm.procedure
    }

    // calling apis
    this.loading = true;
    const calls = [
      this.appointmentService.getDoctorsAvailableHours(req),
      this.userService.getDoctorsSuggestionInfo(doctorsIds)
    ]
    forkJoin(calls)
    .subscribe(
      ([res1,res2]) => {
        setTimeout(() => {
        this.selectedDoctorsInfo = res2 as DoctorSuggestionInfo[];
        this.appointmentSuggestions = this.createAppointmentSuggestions(res1 as DoctorsAvailableHours[]);
        this.loading = false;
        }, 500);
      },
      err => {
        console.log(err);
      }
    )
  }

  createAppointmentSuggestions(doctorsAvailableHours : DoctorsAvailableHours[]): AppointmentSuggestion[]{
    const list : AppointmentSuggestion[] = [];
    doctorsAvailableHours.forEach(el => {
      const doctorInfo = this.selectedDoctorsInfo.find((de : any) => de.id === el.doctorId) // should be always found
      el.dates.forEach(d => {
        const obj : AppointmentSuggestion = {
          doctorId: doctorInfo!.id,
          doctorName: doctorInfo!.firstName + " " + doctorInfo!.lastName,
          doctorSpeciality: doctorInfo!.speciality,
          hospitalName: doctorInfo!.hospital,
          profileImage : doctorInfo!.profileImage,
          date: d.date,
          hours: d.hours,
        }
        list.push(obj)
      });
    });
    return this.sortAppointmentsByDate(list);
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
        const selectedHospitalsIds = this.formOptional.get('hospitals')?.value;
        if (selectedHospitalsIds.length > 0) {
          this.filteredDoctorsOptions = this.doctorsOptions.filter((el: any) => selectedHospitalsIds.includes(el.hospitalId));
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

  onSuggestionSelection(data : any){
    this.sugestionSelection = data;
  }

  createAppointmentSummary(){
    const doctorInfo = this.selectedDoctorsInfo.find(el => el.id === this.sugestionSelection.doctorId)
    const procedure = this.procedureOptions.find((el : any) => el.id ===this.formMandatory.get('procedure')?.value)

    this.appointmentSummary = {
      doctorName: doctorInfo!.firstName + " " +doctorInfo!.lastName,
      doctorSpeciality: doctorInfo!.speciality,
      procedureName: procedure.name,
      hospitalName: doctorInfo!.hospital,
      profileImage: doctorInfo?.profileImage,
      date: this.sugestionSelection.date,
      time: this.sugestionSelection.time,
      price: procedure.price,
      duration:procedure.duration
    }
  }

  resetStep2(){
    this.formOptional.get('doctors')?.setValue([]);
    this.formOptional.get('hospitals')?.setValue([]);
  }

  resetStep3(){
  this.sugestionSelection = undefined;
  }

  saveAppointment() {
    const doctorExtended = this.selectedDoctorsInfo.find(el => el.id === this.sugestionSelection.doctorId)
    const procedure = this.procedureOptions.find((el : any) => el.id ===this.formMandatory.get('procedure')?.value)
    const appointment : NewAppointment ={
      date: this.sugestionSelection.date,
      time: this.sugestionSelection.time,
      procedureId: procedure.id,
      doctorId: this.sugestionSelection.doctorId,
      patientId: this.userService.getPatientIdFromLocalStorage()!
    }

    this.loading = true;
    this.appointmentService.createAppointment(appointment).subscribe(
      res =>{
        this.loading = false;
        this.dialogRef.close(true);
      },
      err =>{
        console.log(err)
        this.dialogRef.close(false);
      }
    );
  }

}
