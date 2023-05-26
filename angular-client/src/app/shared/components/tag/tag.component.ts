import { Component, Input, OnChanges, SimpleChanges } from '@angular/core';

@Component({
  selector: 'app-tag',
  templateUrl: './tag.component.html',
  styleUrls: ['./tag.component.css']
})
export class TagComponent implements OnChanges {

  @Input() text: string = '';
  @Input() type: string = "default"
  color: string = 'white';
  bgColor: string = 'green';

  ngOnChanges(changes: SimpleChanges): void {
    if (changes['type']) {
      switch (this.type) {
        case 'IN_PROGRESS':
          this.color = 'white';
          this.bgColor = '#6D71F9'
          break;
        case 'UPCOMING':
          this.color = 'white';
          this.bgColor = '#54C1FB'
          break;
        case 'REVIEWED':
          this.color = 'white';
          this.bgColor = '#272848'
          break;

        default:
          break;
      }
    }
  }
}
