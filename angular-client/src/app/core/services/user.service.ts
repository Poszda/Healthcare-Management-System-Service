import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private user : any = null;
  constructor(private http: HttpClient,private router : Router) {
  }

  getUser(){
    return {};
  }

  getUserType(){
    return 'pacient'
    // return 'doctor'
    //return 'admin'
  }

  login(email : string , password: string){
    return this.http.post<any>("http://localhost:8080/login",{email:email, password: password}).subscribe(
      res =>{
        this.user = res;
        localStorage.setItem('user',JSON.stringify(this.user));
        this.router.navigate(['']);
      },
      err =>{
        console.log(err)
      }
    );
  }

  logout(){

  }

  autoLogin(){
    if(localStorage.getItem('user')){
      const localstorgeUser = localStorage.getItem('user')
      this.user = localstorgeUser? JSON.parse(localstorgeUser) : null;
    }
  }

  isAuthenticated(){
    return false;
  }

  

}
