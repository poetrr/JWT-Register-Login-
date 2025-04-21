package com.AttendanceTracker.Prelag.Service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class HashService {
	private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	 public String hashIt(String email, String plainPassword) {
	        String hashedPassword = passwordEncoder.encode(plainPassword);
	        return hashedPassword;
	        
	 }
	 public boolean verifyPassword(String plainPassword, String hashedPassword) {
		boolean match = passwordEncoder.matches(plainPassword, hashedPassword);
		System.out.println("Verifying '" + plainPassword + "' against '" + hashedPassword + "' -> " + match);
		return match;
	}
	
}
