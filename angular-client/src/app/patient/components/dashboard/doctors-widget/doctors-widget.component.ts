import { Component, Input, OnInit } from '@angular/core';
import { DoctorSearch } from 'src/app/patient/models/doctor-search.model';
import { SpecialitiesService } from 'src/app/patient/services/specialities.service';
import { UserService } from 'src/app/patient/services/user.service';

@Component({
  selector: 'app-doctors-widget',
  templateUrl: './doctors-widget.component.html',
  styleUrls: ['./doctors-widget.component.css']
})
export class DoctorsWidgetComponent implements OnInit {
  specialities: any[] = []
  doctors : DoctorSearch[] = []
  notice = false;


  disableButton: boolean = true;
  form = {
    name: '',
    specialityId: null
  }

  constructor(private specialitiesService: SpecialitiesService, private userService : UserService) { }

  ngOnInit(): void {
    this.getSpecialities();
  }

  getSpecialities() {
    this.specialitiesService.getSpecialities().subscribe(
      res => {
        this.specialities = res;
      },
      err => {
        console.log(err)
      }
    )
  }

  searchDoctors() {
    this.userService.getSearchedDoctors(this.form).subscribe(
      res => {
        this.doctors = res;
        if(res.length === 0) this.notice = true;
        else this.notice = false;
      },
      err => {
        console.log(err)
      }
    );
  }
  


}
