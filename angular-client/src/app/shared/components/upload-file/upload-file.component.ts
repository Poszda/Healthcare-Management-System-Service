import { Component, EventEmitter, Input, Output } from '@angular/core';

@Component({
  selector: 'app-upload-file',
  templateUrl: './upload-file.component.html',
  styleUrls: ['./upload-file.component.css']
})
export class UploadFileComponent {
  @Input() buttonMessage: string = 'Upload file';
  @Output() fileUpload: EventEmitter<File> = new EventEmitter();
  fileName: string = '';

  onFileSelected($event: any) {
    const file: File = $event?.target?.files[0];
    if (file) {
      this.fileName = file.name;
      this.fileUpload.emit(file);
    }
  }
}
