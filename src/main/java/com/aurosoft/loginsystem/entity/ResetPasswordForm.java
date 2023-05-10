package com.aurosoft.loginsystem.entity;

import com.aurosoft.loginsystem.PasswordMatches;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;;


@PasswordMatches
public class ResetPasswordForm {
	
	 @NotBlank(message = "Password is required")
	    @Size(min = 6, max = 20, message = "Password must be between 6 and 20 characters")
//	 @PasswordMatches(message = "Passwords do not match")
	    private String password;

	 @NotBlank(message = "Confirm password is required")
	// @PasswordMatches(message = "Passwords do not match")
	    private String confirmPassword;

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public ResetPasswordForm(
			@NotBlank(message = "Password is required") @Size(min = 6, max = 20, message = "Password must be between 6 and 20 characters") String password,
			@NotBlank(message = "Confirm password is required") String confirmPassword) {
		super();
		this.password = password;
		this.confirmPassword = confirmPassword;
	}

	 
	 
}
