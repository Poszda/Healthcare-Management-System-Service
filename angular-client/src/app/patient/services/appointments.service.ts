import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable()
export class AppointmentsService {

  constructor(private http: HttpClient) { }

  getFormOptionalOptions(county : string, procedureId : number){
    return this.http.get<any>(`http://localhost:8080/api/appointments/getHospitalsAndDoctorsRecommendations/${county}/${procedureId}`);
  }

}
