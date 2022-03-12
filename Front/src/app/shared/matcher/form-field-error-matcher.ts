import { FormControl } from '@angular/forms';
import { ErrorStateMatcher } from '@angular/material/core';

export class ErrorMatcher implements ErrorStateMatcher {
    isErrorState(control: FormControl | null): boolean {
        return (control.invalid && control.dirty) || (control.invalid && control.touched);
    }
}
