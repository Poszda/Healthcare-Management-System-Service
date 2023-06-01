import { Component, Inject } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';

@Component({
  selector: 'app-confirmation-dialog',
  templateUrl: './confirmation-dialog.component.html',
  styleUrls: ['./confirmation-dialog.component.css']
})
export class ConfirmationDialogComponent {
  
  message : string = ''
  
  constructor(public dialogRef: MatDialogRef<ConfirmationDialogComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any){
      this.message = this.data?.message
    }
  
    decline(){
      this.dialogRef.close(false)
    }
    accept(){
      this.dialogRef.close(true)
    }
  

}
