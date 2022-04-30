import { Component, Input, OnInit } from '@angular/core';
import { FormGroup } from "@angular/forms";
import { NgxFileDropEntry } from "ngx-file-drop";
import { MatSnackBar } from "@angular/material/snack-bar";

@Component({
    selector: 'app-modify-apartment-images',
    templateUrl: './modify-apartment-images.component.html',
    styleUrls: ['./modify-apartment-images.component.scss']
})
export class ModifyApartmentImagesComponent implements OnInit {

    @Input() apartmentForm: FormGroup;
    files: File[] = [];
    loadedFilesToShow = [];

    constructor(private snackBarService: MatSnackBar) {
    }

    ngOnInit(): void {
    }

    onFileChange(droppedFiles: NgxFileDropEntry[]): void {
        droppedFiles.forEach(file => {
            const fileEntry = file.fileEntry as FileSystemFileEntry;
            fileEntry.file((fileEvent: File) => this.processFile(fileEvent));
        });
    }

    private processFile(file: File): void {
        if (!this.isFileTypeValid(file)) {
            return;
        }

        const reader = new FileReader();
        reader.readAsDataURL(file);

        reader.onload = uploadedFile => {
            this.loadedFilesToShow.push(uploadedFile.target.result.toString());
            this.addImageToFormControl(file);
        }
    }

    private isFileTypeValid(file: File): boolean {
        if ((file.type === 'image/jpeg') || (file.type === 'image/png')) {
            return true;
        }
        this.snackBarService.open('Wrong format (only jpeg or png)', '', {duration: 3000});
        return false;
    }

    private addImageToFormControl(fileImage: File): void {
        this.files.push(fileImage);
        this.patchForm();
    }

    removeFile(fileToDelete): void { // TODO
        this.files.filter(file => file !== fileToDelete);
        this.loadedFilesToShow.filter(file => file !== fileToDelete);
        this.patchForm();
    }

    private patchForm(): void {
        this.apartmentForm.patchValue({
            images: this.files
        });
        this.apartmentForm.markAsDirty();
    }
}
