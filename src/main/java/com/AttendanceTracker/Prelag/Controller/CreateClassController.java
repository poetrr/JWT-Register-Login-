package com.AttendanceTracker.Prelag.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.AttendanceTracker.Prelag.Model.User;
import com.AttendanceTracker.Prelag.Service.CreateClassService;
import com.AttendanceTracker.Prelag.Service.JwtUtil;
import com.AttendanceTracker.Prelag.Service.LoginService;

@RestController
@RequestMapping("/classroom")
@CrossOrigin(origins="*")
public class CreateClassController {

   
    
    @PostMapping(path="/createclass")
    public ResponseEntity<?> createClass(
        @RequestHeader("Authorization") String token,
        @RequestBody CreateClassDTO createClassRequest
        ) {
            try {
                System.out.println(createClassRequest.getSubjectName());
                System.out.println(token);
                boolean validation = JwtUtil.validateToken(token,createClassRequest.getEmail());
                if (!validation) {
                    return ResponseEntity
                        .status(HttpStatus.UNAUTHORIZED)
                        .body("Invalid or expired token");
                }

                CreateClassService create = new CreateClassService();
                CreateClassDTO createClassDTO = new CreateClassDTO();

                String email = JwtUtil.extractEmailFromToken(token);
                User user = create.getUserByEmail(email);
                createClassDTO.setCreatorId(user.getId());

                boolean creationResponseFromDB = create.SaveClass(
                    createClassDTO.getCreatorId(),
                    createClassRequest.getClassName(),
                    createClassRequest.getSubjectName()
                );

                if (creationResponseFromDB) {
                    return ResponseEntity
                        .status(HttpStatus.OK)
                        .body("Class created successfully");
                } else {
                    return ResponseEntity
                        .status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body("Failed to create class");
                }

            } catch (Exception e) {
                return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error: " + e.getMessage());
            }
        }
}
class CreateClassDTO{
    private int classId;
    private int creatorId;
    private String email;
    public int getClassId() {
        return classId;
    }
    public void setClassId(int classId) {
        this.classId = classId;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    private String className;
    private String subjectName;
    public void setId(int classId){
        this.classId=classId;
    }
    public void setCreatorId(int creatorId){
        this.creatorId=creatorId;
    }
    public void setClassName(String className){
        this.className=className;
    }
    public void setSubjectName(String subjectName){
        this.subjectName=subjectName;
    }
    public int getId(){
        return this.classId;
    }
    public String getClassName(){
        return this.className;
    }
    public String getSubjectName(){
        return this.subjectName;
    }
    public int getCreatorId(){
        return this.creatorId;
    }
}