import { Injectable } from '@angular/core';

@Injectable()
export class UserService {

  constructor() { }

  getHospitalFromLocalStorage(){
    const item = localStorage.getItem('admin')
    if(item) return JSON.parse(item)?.hospital;
    return null;
  }
}
