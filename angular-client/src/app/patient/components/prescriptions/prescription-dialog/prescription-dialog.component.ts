import { Component,Inject } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { DiagnosticExtended } from 'src/app/patient/models/diagnostic-extended.model';

@Component({
  selector: 'app-prescription-dialog',
  templateUrl: './prescription-dialog.component.html',
  styleUrls: ['./prescription-dialog.component.css']
})
export class PrescriptionDialogComponent {
  diagnostic : DiagnosticExtended
  constructor(public dialogRef: MatDialogRef<PrescriptionDialogComponent>,
    @Inject(MAT_DIALOG_DATA) public data: DiagnosticExtended){
      this.diagnostic = data;
    }
}
