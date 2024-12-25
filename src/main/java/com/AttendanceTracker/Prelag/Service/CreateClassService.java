package com.AttendanceTracker.Prelag.Service;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class CreateClassService {

    
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public boolean validateToken(String token) {
        return JwtUtil.validateToken(token);
    }

    public String extractEmailFromToken(String token) {
        return JwtUtil.extractEmailFromToken(token);
    }
    
    public Integer getUserIdByEmail(String email) {
        String sql = "SELECT user_id FROM users WHERE email = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{email}, Integer.class);
    }

    // Method to generate a random 6-digit code
    private String generateClassCode() {
        Random random = new Random();
        int code = 100000 + random.nextInt(900000);  // Generates a 6-digit number
        return String.valueOf(code);
    }

    // Method to create a class in the database
    public String createClass(int userId,String className, String subjectName, float minAttendance) {
        // Generate unique class code
        String classCode = generateClassCode();
        

        // SQL Query to insert class data into the table
        String sql = "INSERT INTO classes (creator_id,class_name, subject_name, min_attendance, class_code) VALUES (?,?, ?, ?, ?)";

        try {
            // Insert the class data into the table
            jdbcTemplate.update(sql,userId, className, subjectName, minAttendance, classCode);
            return classCode;  // Return the generated class code
        } catch (Exception e) {
            // Handle any exceptions (e.g., SQL exceptions)
            e.printStackTrace();
            return null;
        }
    }
}
