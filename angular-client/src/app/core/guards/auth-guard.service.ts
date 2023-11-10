import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, CanLoad, Route, Router, RouterStateSnapshot, UrlTree } from '@angular/router';
import { Observable } from 'rxjs';
import { AuthService } from '../services/auth.service';

@Injectable({
  providedIn: 'root'
})
export class AuthGuardService implements CanLoad, CanActivate {
  constructor(private router: Router, private authService: AuthService) {
  }

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): boolean | UrlTree | Observable<boolean | UrlTree> | Promise<boolean | UrlTree> {
    console.log('canActivate called')
    if(this.authService.isAuthenticated()){
      return true
    }
    else{
      this.router.navigate(['authentication'])
      return false;
    } 
  }

  
  canLoad(route: Route): boolean {
    if(route.path === this.authService.getUserRoute()){
      return true;
    }
    this.router.navigate(['not-found'])
    return false;
  }
}
