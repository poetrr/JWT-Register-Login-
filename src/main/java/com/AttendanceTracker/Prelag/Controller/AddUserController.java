package com.AttendanceTracker.Prelag.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.AttendanceTracker.Prelag.Service.EmailService;
import com.AttendanceTracker.Prelag.Service.JwtUtil;
import com.AttendanceTracker.Prelag.Service.OTPValidationService;
import com.AttendanceTracker.Prelag.Service.OtpValidationRequest;
import com.AttendanceTracker.Prelag.Service.StoringService;


@RestController
@RequestMapping(path = "/classroom")
@CrossOrigin(origins = "*") // Allow all origins (any port)
public class AddUserController {

    @Autowired
    private EmailService emailService;//Service to send mails

    
    @Autowired
    private OTPValidationService otpValidationService;//Service to generate OTP and validateOTP
    @Autowired
    private StoringService storeDB;//Service to store the data to DB


    @PostMapping(path = "/createUser")
    public ResponseEntity<String> addUser(@RequestBody TemporaryEmail user) {
        String email = user.getEmail();
        
        
        String otp = emailService.generateOtp();

        // Send OTP and store it
        emailService.sendOtpEmail(email, otp);
        otpValidationService.storeOtp(email, otp);

        otpValidationService.cleanUpExpiredOtps();
        
        return ResponseEntity.ok("OTP sent to " + email+otp);
    }

    @PostMapping(path = "/validateOtp")
    public ResponseEntity<String> validateOtp(@RequestBody OtpValidationRequest request) {
        String email = request.getEmail();
        String enteredOtp = request.getOtp();
        String password=request.getPassword();
        String username=request.getUsername();
        
        
        String validationMessage = otpValidationService.validateOtp(email, enteredOtp);

        if (!validationMessage.equals("OTP validated successfully")) {
            return ResponseEntity.badRequest().body(validationMessage);
        }
        
       String dbresponse=storeDB.StoreToDB(username, email, password);
        if(!dbresponse.equals("Stored To DB")) {
        	return ResponseEntity.badRequest().body(dbresponse);
        }
        String token = JwtUtil.generateToken(email);
        return ResponseEntity.ok("User Created"+token);
           
    }
}


class TemporaryEmail{
	String email;
	public void setEmail(String email) {
		this.email=email;
	}
	public String getEmail() {
		return email;
	}
}
