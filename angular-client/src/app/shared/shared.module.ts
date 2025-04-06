import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { NavbarComponent } from './components/navbar/navbar.component';
import { RouterModule } from '@angular/router';
import { WidgetDotContainerComponent } from './components/widgets-containers/widget-dot-container/widget-dot-container.component';
import { WidgetLinkContainerComponent } from './components/widgets-containers/widget-link-container/widget-link-container.component';
import {ChartModule} from 'primeng/chart';
import { WidgetSimpleContainerComponent } from './components/widgets-containers/widget-simple-container/widget-simple-container.component';
import { AppointmentsWidgetComponent } from './components/widgets/appointments-widget/appointments-widget.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { CalendarModule } from 'primeng/calendar';
import {InputTextModule} from 'primeng/inputtext';
import { InputTextareaModule } from 'primeng/inputtextarea';
import {AutoCompleteModule} from 'primeng/autocomplete';
import {ToastModule} from 'primeng/toast';
import {MultiSelectModule} from 'primeng/multiselect';
import {DropdownModule} from 'primeng/dropdown';
import {ProgressSpinnerModule} from 'primeng/progressspinner';

import {MatDialogModule} from '@angular/material/dialog';
import {MatStepperModule} from '@angular/material/stepper';
import {MatProgressBarModule} from '@angular/material/progress-bar';
import { TableModule } from 'primeng/table';
import { TagComponent } from './components/tag/tag.component';
import { ConfirmationDialogComponent } from './components/confirmation-dialog/confirmation-dialog.component';
import { DoctorProfileComponent } from './pages/doctor-profile/doctor-profile.component';
import { PatientProfileComponent } from './pages/patient-profile/patient-profile.component';
import { ProfileAboutMeComponent } from './components/profile-about-me/profile-about-me.component';
import { ProfileSummaryComponent } from './components/profile-summary/profile-summary.component';
import { ProfileInformationCardComponent } from './components/profile-information-card/profile-information-card.component';
import { ProfileMedicalHistoryComponent } from './components/profile-medical-history/profile-medical-history.component';
import { ProfileTreatmentsHistoryComponent } from './components/profile-treatments-history/profile-treatments-history.component';
import {RadioButtonModule} from 'primeng/radiobutton';
import { InputNumberModule } from 'primeng/inputnumber';
import { UploadFileComponent } from './components/upload-file/upload-file.component';
import { NothingHereComponent } from './pages/nothing-here/nothing-here.component';


@NgModule({
  declarations: [
    NavbarComponent,
    WidgetDotContainerComponent,
    WidgetLinkContainerComponent,
    WidgetSimpleContainerComponent,
    AppointmentsWidgetComponent,
    TagComponent,
    ConfirmationDialogComponent,
    DoctorProfileComponent,
    PatientProfileComponent,
    ProfileAboutMeComponent,
    ProfileSummaryComponent,
    ProfileInformationCardComponent,
    ProfileMedicalHistoryComponent,
    ProfileTreatmentsHistoryComponent,
    UploadFileComponent,
    NothingHereComponent
  ],
  imports: [
    CommonModule,
    RouterModule, 
    FormsModule,
    ReactiveFormsModule,
    ChartModule, //prime ng
    CalendarModule,
    InputTextModule,
    InputTextareaModule,
    InputNumberModule,
    AutoCompleteModule,
    ToastModule,
    TableModule,
    MatDialogModule,
    MatStepperModule,
    MultiSelectModule,
    DropdownModule,
    ProgressSpinnerModule,
    MatProgressBarModule,
    RadioButtonModule,
  ],
  exports:[
    FormsModule,
    ReactiveFormsModule,
    NavbarComponent,
    WidgetDotContainerComponent,
    WidgetLinkContainerComponent,
    WidgetSimpleContainerComponent,
    AppointmentsWidgetComponent,
    ChartModule,
    MatDialogModule,
    CalendarModule,
    InputTextModule,
    InputTextareaModule,
    InputNumberModule,
    AutoCompleteModule,
    RadioButtonModule,
    ToastModule,
    TableModule,
    MatStepperModule,
    MultiSelectModule,
    DropdownModule,
    ProgressSpinnerModule,
    MatProgressBarModule,
    TagComponent,
    DoctorProfileComponent,
    PatientProfileComponent,
    ProfileAboutMeComponent,
    ProfileSummaryComponent,
    ProfileInformationCardComponent,
    ProfileMedicalHistoryComponent,
    ProfileTreatmentsHistoryComponent,
    UploadFileComponent,
    NothingHereComponent
  ]
})
export class SharedModule { }
