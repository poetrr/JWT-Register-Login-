package com.AttendanceTracker.Prelag.Controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.AttendanceTracker.Prelag.Service.GetTeacherClassService;

@RestController
@RequestMapping(path = "/Prelag")
@CrossOrigin(origins = "*")
public class StaffClassesController {

    @Autowired
    private GetTeacherClassService getTeacherClassService;

    @PostMapping("/StaffClasses")
    public ResponseEntity<?> getStaffClasses(@RequestHeader("Authorization") String authHeader) {
        try {
            // Extract token from "Bearer token"
            String token = authHeader.replace("Bearer ", "");

            // Validate the token
            if (!getTeacherClassService.validateToken(token)) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid or expired token.");
            }

            // Extract email from the token
            String email = getTeacherClassService.extractEmailFromToken(token);

            // Get user ID by email
            Integer userId = getTeacherClassService.getUserIdByEmail(email);

            // Fetch classes created by the user
            List<Map<String, Object>> classes = getTeacherClassService.getClassesByUserId(userId);

            return ResponseEntity.ok(classes);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred: " + e.getMessage());
        }
    }
}