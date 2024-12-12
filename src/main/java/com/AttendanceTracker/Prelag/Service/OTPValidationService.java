package com.AttendanceTracker.Prelag.Service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class OTPValidationService {

    // In-memory storage for OTPs and their timestamps
    private final Map<String, OtpDetails> otpStore = new HashMap<>();

    // Store the OTP with its generated time
    public void storeOtp(String email, String otp) {
        otpStore.put(email, new OtpDetails(otp, System.currentTimeMillis()));
    }
    @Scheduled(fixedRate = 1000) // Run every 5 minutes
    public void cleanUpExpiredOtps() {
        otpStore.forEach((key, details) -> {
            System.out.println("Remaining Key: " + key);
            System.out.println("OTP: " + details.getOtp());
            System.out.println("Generated Time: " + details.getGeneratedTime());
            System.out.println("-----");
        });
        long currentTime = System.currentTimeMillis();
        otpStore.entrySet().removeIf(entry -> currentTime - entry.getValue().getGeneratedTime() > 60000);
        
        // This will remove all OTPs that are older than 60 seconds
    }
    
    // Validate the OTP
    public String validateOtp(String email, String enteredOtp) {
        if (!otpStore.containsKey(email)) {
            return "No OTP found for this email.";
        }

        OtpDetails otpDetails = otpStore.get(email);
        long currentTime = System.currentTimeMillis();

        
        // Check if the OTP has expired that is 60 seconds limi
        if ((currentTime - otpDetails.getGeneratedTime()) > 60000) {
            otpStore.remove(email); // Remove expired OTP
            return "OTP expired.";
        }

        // Check if the entered OTP matches the stored OTP
        if (!otpDetails.getOtp().equals(enteredOtp)) {
            return "Invalid OTP.";
        }
        
        otpStore.remove(email); // Remove validated OTP
        return "OTP validated successfully";
    }

    // Internal class to store OTP and its generated time
    private static class OtpDetails {
        private final String otp;
        private final long generatedTime;

        public OtpDetails(String otp, long generatedTime) {
            this.otp = otp;
            this.generatedTime = generatedTime;
        }

        public String getOtp() {
            return otp;
        }

        public long getGeneratedTime() {
            return generatedTime;
        }
    }
}
