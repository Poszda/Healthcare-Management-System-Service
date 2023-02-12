import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { UserType } from '../models/user-type.enum';
import { User } from '../models/user.model';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private user?: User;
  constructor(private http: HttpClient,private router : Router) {
    this.autoLogin();
  }

  getUser(){
    return this.user;
  }

  getUserType(){
    return this.user?.type;
  }

  isAuthenticated(){
    if(this.user) return true
    return false;
  }

  login(email : string , password: string){
    return this.http.post<User>("http://localhost:8080/api/auth/login",{email:email, password: password}).subscribe(
      res =>{
        this.user = res;
        localStorage.setItem('user',JSON.stringify(this.user));
        this.router.navigate([this.getUserType()]);
      },
      err =>{
        console.log(err)
      }
    );
  }

  autoLogin(){
    if(localStorage.getItem('user')){
      const localstorgeUser = localStorage.getItem('user')
      this.user = localstorgeUser? JSON.parse(localstorgeUser) : undefined;
    }
  }

  logout(){
    this.router.navigate(['login']);
    localStorage.removeItem('user');
  }
}
