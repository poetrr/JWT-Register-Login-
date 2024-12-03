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
}
