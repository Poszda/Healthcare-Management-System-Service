import { Component,EventEmitter,Input, Output } from '@angular/core';
import { DiagnosticExtended } from 'src/app/patient/models/diagnostic-extended.model';

@Component({
  selector: 'app-prescriptions-widget',
  templateUrl: './prescriptions-widget.component.html',
  styleUrls: ['./prescriptions-widget.component.css']
})
export class PrescriptionsWidgetComponent {
  @Input() diagnostics : DiagnosticExtended[]= []
  @Output() seeMore : EventEmitter<DiagnosticExtended> = new EventEmitter();

  openExtendedDiagnostic(diagnostic : DiagnosticExtended){
    this.seeMore.emit(diagnostic);
  }
}
