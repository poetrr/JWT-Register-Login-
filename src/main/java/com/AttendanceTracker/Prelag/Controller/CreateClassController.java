package com.AttendanceTracker.Prelag.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.AttendanceTracker.Prelag.Service.CreateClassService;
import com.fasterxml.jackson.annotation.JsonProperty;

@RestController
@RequestMapping("/Prelag")
@CrossOrigin(origins="*")
public class CreateClassController {

    @Autowired
    private CreateClassService createClassService;

    @PostMapping("/createClass")
    public ResponseEntity<?> createClass(@RequestBody CreateClassRequest request,@RequestHeader("Authorization") String authHeader) {
    
        try{
            String token=authHeader.replace("Bearer","");
            if(!createClassService.validateToken(token)){
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid or expired token.");
            }
            String email=createClassService.extractEmailFromToken(token);
            Integer userId=createClassService.getUserIdByEmail(email);
            request.setUserId(userId);
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred : "+ e.getMessage());

        }

        System.out.println(request.getClassName());
        // Call the service to create the class and get the class code
        String classCode = createClassService.createClass(request.getUserId(),request.getClassName(), request.getSubjectName(), request.getMinAttendance());
        
        // Return the response with the generated class code
        if (classCode != null) {
            return ResponseEntity.ok("Class created successfully! Your unique class code is: " + classCode);
        } else {
            return ResponseEntity.status(500).body("Failed to create class. Please try again later.");
        }
    }

    // Create a request class to map incoming JSON request body
    public static class CreateClassRequest {
        @JsonProperty("class_name")
        private String className;
        @JsonProperty("subject_name")
        private String subjectName;
        @JsonProperty("min_attendance")
        private float minAttendance;
        private int userId;
        // Getters and Setters
        public int getUserId() {
            return userId;
        }
        public void setUserId(int userId) {
            this.userId = userId;
        }
        public String getClassName() {
            return className;
        }

        public void setClassName(String className) {
            this.className = className;
        }

        public String getSubjectName() {
            return subjectName;
        }

        public void setSubjectName(String subjectName) {
            this.subjectName = subjectName;
        }

        public float getMinAttendance() {
            return minAttendance;
        }

        public void setMinAttendance(float minAttendance) {
            this.minAttendance = minAttendance;
        }
    }
}