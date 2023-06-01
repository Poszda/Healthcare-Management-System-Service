import { Component, EventEmitter, Input ,OnChanges, Output, SimpleChanges} from '@angular/core';
import * as moment from 'moment';
import { DiagnosticExtended } from 'src/app/patient/models/diagnostic-extended.model';

@Component({
  selector: 'app-prescription-card',
  templateUrl: './prescription-card.component.html',
  styleUrls: ['./prescription-card.component.css']
})
export class PrescriptionCardComponent implements OnChanges {
  @Input() diagnostic : DiagnosticExtended | undefined;
  @Output() seeMore : EventEmitter<DiagnosticExtended> = new EventEmitter();

  ngOnChanges(changes: SimpleChanges): void {
  }

  getDateOnly(date : string){
    moment(date).format('YYYY-MM-DD')
  }

  openSeeMoreDialog(){
    this.seeMore.emit(this.diagnostic);
  }

}
