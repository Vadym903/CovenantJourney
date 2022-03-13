import { Directive, ElementRef, HostListener, Input } from '@angular/core';
import { FormControl } from '@angular/forms';

@Directive({
    // tslint:disable-next-line:directive-selector
    selector: '[error-control]'
})
export class ErrorFormControlDirective {

    constructor(private element: ElementRef) { }

    @Input('error-control')
    private formControl: FormControl;

    @HostListener('keyup')
    handleKeyUp() {
        if (this.formControl.errors) {
            this.element.nativeElement.classList.add('error-field');
        } else {
            this.element.nativeElement.classList.remove('error-field');
        }
    }
}
