import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  constructor() {
  }

  getUser(){
    return {};
  }

  getUserType(){
    return 'pacient'
    // return 'doctor'
    //return 'admin'
  }

  isAuthenticated(){
    return false;
  }

  

}
