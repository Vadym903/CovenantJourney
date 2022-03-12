import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';

@Component({
  selector: 'app-photo-upload',
  templateUrl: './photo-upload.component.html',
  styleUrls: ['./photo-upload.component.scss']
})
export class PhotoUploadComponent implements OnInit {
  @Input() imgAlreadyExists: string;
  @Output() imageEmitter = new EventEmitter();
  imgForShow: string | ArrayBuffer = null;
  defaultImg = 'assets\\img\\default-img.png';
  allowedFileTypes: string[] = ['image/jpeg', 'image/png'];

  ngOnInit(): void {
    this.initImgForShow();
  }

  clearData(): void {
    this.initImgForShow();
    this.imageEmitter.emit(null);
  }

  uploadFile(uploadEvent: any): void {
    const file = uploadEvent.target.files[0];
    if (file) {
      this.processUploadedFile(file);
    }
  }

  private processUploadedFile(file: File): void {
    if (this.allowedFileTypes.indexOf(file.type) !== -1) {
      const reader = new FileReader();
      reader.readAsDataURL(file);
      reader.onload = (uploadedFile: any) => {
        this.imgForShow = reader.result;
        this.imageEmitter.emit(file); // TODO
      };
      return;
    }
    // show snack message
  }

  private initImgForShow(): void {
    this.imgForShow = this.imgAlreadyExists || this.defaultImg;
  }
}
