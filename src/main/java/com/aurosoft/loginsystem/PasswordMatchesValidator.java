package com.aurosoft.loginsystem;

import com.aurosoft.loginsystem.entity.ResetPasswordForm;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PasswordMatchesValidator implements ConstraintValidator<PasswordMatches, Object> {

	
	  @Override
	    public void initialize(PasswordMatches constraintAnnotation) {
	    }

	    @Override
	    public boolean isValid(Object obj, ConstraintValidatorContext context) {
	        ResetPasswordForm resetPasswordForm = (ResetPasswordForm) obj;
	        return resetPasswordForm.getPassword().equals(resetPasswordForm.getConfirmPassword());
	    }
	    
	   
}
