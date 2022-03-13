import { FormGroup } from '@angular/forms';

export class FormErrorsUtils {

    public getErrorMessages(fieldName: string, form: FormGroup): string[] {
        const result = [];

        if (form.touched && form.errors) {

            // tslint:disable-next-line:forin prefer-const
            for (let error in form.errors) {
                switch (error) {
                    case 'required':
                        result.push('is required field');
                        break;
                    case 'maxLength':
                        result.push('maxLength');
                        break;

                    case 'required':
                        result.push('is required field');
                        break;
                }
            }
        }
        return result;
    }

}
