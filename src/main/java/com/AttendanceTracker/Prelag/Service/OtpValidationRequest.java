package com.AttendanceTracker.Prelag.Service;

public class OtpValidationRequest {
	private String username;
   

	private String email;
    private String otp;
    private String password;
    public String getUsername() {
		return username;
	}

	public void setUserName(String username) {
		this.username = username;
	}
    public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }
}