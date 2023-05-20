import { Injectable } from '@angular/core';
import { Subject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AlertService {
  
  alert : Subject<any> = new Subject<any>();
  constructor() { }

  showSuccess(message : string){
    const alert = {
      message : message,
      type : 'success'
    }
    this.alert.next(alert);
  }

  showError(message: string) {
    const alert = {
      message : message,
      type : 'error'
    }
    this.alert.next(alert);
  }
}
