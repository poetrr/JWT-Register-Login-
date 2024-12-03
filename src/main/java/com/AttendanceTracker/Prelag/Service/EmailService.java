package com.AttendanceTracker.Prelag.Service;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
@Service
public class EmailService {
	@Autowired
	private JavaMailSender mail;
	
	public String generateOtp() {
		
		Random rand=new Random();
		int otp=100000+rand.nextInt(900000);
		return String.valueOf(otp);
	}
	
	public void sendOtpEmail(String recipientEmail,String otp) {
		SimpleMailMessage message=new SimpleMailMessage();
		
		message.setTo(recipientEmail);
		message.setSubject("Your OTP code");
		message.setText("Your OTP code is : " + otp);
		
		mail.send(message);
		
	}
	
	
}
