import { AbstractControl, ValidationErrors, ValidatorFn } from "@angular/forms";

/* if the control has control.errors = null then it will be valid, otherwise not.
 (ex. control.erros = {} it s not valid) */

export function passwordMatch(field1 : string, field2 : string): ValidatorFn {
   return (control: AbstractControl): ValidationErrors | null => {
        const password = control.get(field1);
        const confirmPassword = control.get(field2);
    
        if (password && confirmPassword) {
            const confirmPasswordErrors = confirmPassword.errors || {};
            if (password.value === confirmPassword.value) {
                delete confirmPasswordErrors["passwordsNotMatching"];
                if (Object.keys(confirmPasswordErrors).length === 0) {
                    confirmPassword.setErrors(null);
                } else {
                    confirmPassword.setErrors(confirmPasswordErrors);
                }
                return null;
            }
            confirmPasswordErrors["passwordsNotMatching"] = true;
            confirmPassword.setErrors(confirmPasswordErrors);
            return { passwordsNotMatching: true };   
        }

        return null;
    };
}