package com.AttendanceTracker.Prelag.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.AttendanceTracker.Prelag.DTOS.UserCreationResponse;
import com.AttendanceTracker.Prelag.Service.EmailService;
import com.AttendanceTracker.Prelag.Service.HashService;
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

    @Autowired
    HashService hashService;
    
    @PostMapping(path = "/sendOtp")
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
    public ResponseEntity<?> validateOtp(@RequestBody OtpValidationRequest request) {
        String email = request.getEmail();
        String enteredOtp = request.getOtp();
        String password=request.getPassword();
        String username=request.getUsername();
        
        
       
        String validationMessage = otpValidationService.validateOtp(email, enteredOtp);
        

        if(validationMessage.equals("OTP validated successfully")){
            String token = JwtUtil.generateToken(email);
            
            System.out.println("Registration - email: " + email);
            System.out.println("Registration - plain password: " + password);
            String hashedPassword = hashService.hashIt(email, password);
            System.out.println("Registration - hashed password: " + hashedPassword);

            String dbresponse=storeDB.StoreToDB(username, email, hashedPassword);
            System.out.println(dbresponse);
            return ResponseEntity.ok(new UserCreationResponse(validationMessage,token ));
        }
        if (!validationMessage.equals("Invalid Otp")) {
            return ResponseEntity.ok(new UserCreationResponse("Invalid Otp", null));
        }
        return ResponseEntity.ok("Couldnot process request");        
    }
}


class TemporaryEmail{
	String email;
    String password;
    String username;
	public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public void setEmail(String email) {
		this.email=email;
	}
	public String getEmail() {
		return email;
	}
}
