import { Component, Input, OnInit } from '@angular/core';
import {MatSnackBar} from '@angular/material/snack-bar';

@Component({
  selector: 'app-widget-dot-container',
  templateUrl: './widget-dot-container.component.html',
  styleUrls: ['./widget-dot-container.component.css']
})
export class WidgetDotContainerComponent implements OnInit {

  @Input() title : string = "Default title"
  showFront : boolean = true;
  constructor(private _snackBar: MatSnackBar) { }

  ngOnInit(): void {
  }

  toggle(){
    this.showFront = !this.showFront
  }

  openSnackBar(message: string, action: string) {
    this._snackBar.open(message, action);
  }

}
