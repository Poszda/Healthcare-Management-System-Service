import { Injectable } from '@angular/core';

@Injectable()
export class DoctorService {

  constructor() { }

  getDoctorFromLocalStorage(){
    const item = localStorage.getItem('doctor')
    if(item) return JSON.parse(item);
    return null;
  }

}
