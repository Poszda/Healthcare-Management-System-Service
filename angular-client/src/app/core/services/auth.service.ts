import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { UserType } from '../models/user-type.enum';
import { User } from '../models/user.model';
import { LoginData } from '../models/login-data.model';
import { Patient } from '../models/patient.model';
import { Doctor } from '../models/doctor.model';
import { Admin } from '../models/admin.model';
import { LoginForm } from '../pages/authentication/models/login-form.model';
import { SignUpForm } from '../pages/authentication/models/signup-form.model';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private user: User | null | undefined;
  constructor(private http: HttpClient, private router: Router) {
    this.autoLogin();
  }

  getUser() {
    return this.user;
  }

  getUserRoute() {
    return this.user?.userType.toLocaleLowerCase();
  }

  isAuthenticated() {
    //if (this.user) return true
    //return false;
    return true;
  }

  signUp(form: SignUpForm) {
    return this.http.post<LoginData>("http://localhost:8080/api/auth/signup", form);
  }

  login(form: LoginForm) {
    return this.http.post<LoginData>("http://localhost:8080/api/auth/login", form); //maybe use rxjs in the future
  }

  handleLogin(result : LoginData){
    this.clearUser(); // clear localstorage
    const [user, userDetails] = this.parseLoginData(result);
    this.user = user;
    localStorage.setItem('user', JSON.stringify(this.user));
    localStorage.setItem(user.userType.toLocaleLowerCase(), JSON.stringify(userDetails))
    this.router.navigate([this.getUserRoute()]);
  }

  parseLoginData(loginData: LoginData): [User, Patient | Doctor | Admin] {
    const user = {
      id: loginData.id,
      firstName: loginData.firstName,
      lastName: loginData.lastName,
      email: loginData.email,
      userType: loginData.userType
    }
    let userTypeData;
    if (loginData.userType === UserType.Patient) userTypeData = loginData.patient;
    else if (loginData.userType === UserType.Doctor) userTypeData = loginData.doctor;
    else userTypeData = loginData.admin;

    return [user, userTypeData!]
  }

  autoLogin() {
    if (localStorage.getItem('user')) {
      const localstorgeUser = localStorage.getItem('user')
      this.user = localstorgeUser ? JSON.parse(localstorgeUser) : undefined;
    }
  }

  logout() {
    this.user = null;
    this.clearUser();
    this.router.navigate(['login']);
  }

  clearUser() {
    localStorage.removeItem('user');
    localStorage.removeItem('patient');
    localStorage.removeItem('doctor');
    localStorage.removeItem('admin');
  }
}
