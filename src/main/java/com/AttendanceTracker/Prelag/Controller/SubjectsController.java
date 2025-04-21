package com.AttendanceTracker.Prelag.Controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.AttendanceTracker.Prelag.DTOS.AlterSubjectsRequest;
import com.AttendanceTracker.Prelag.DTOS.CreateSubjectsRequest;
import com.AttendanceTracker.Prelag.DTOS.SemesterDTO;
import com.AttendanceTracker.Prelag.DTOS.UpdateSubjectRequest;
import com.AttendanceTracker.Prelag.Model.Subject;
import com.AttendanceTracker.Prelag.Service.JwtUtil;
import com.AttendanceTracker.Prelag.Service.SubjectsService;

@RestController
@RequestMapping("/classroom")
@CrossOrigin(origins="*")//Allow all origins
public class SubjectsController {

    @Autowired
    private SubjectsService subjectsService;
    
    @PostMapping("/getSubjects")
    public ResponseEntity<List<Subject>> getSubjectsBySemester(
            @RequestHeader("Authorization") String token,
            @RequestBody GetSemesterRequest request) {
            System.out.println("Entered into the function");
        // Remove "Bearer " from the token if present
    
        if (token.startsWith("Bearer ")) {
            token = token.substring(7);
        }
        System.out.println(JwtUtil.extractEmailFromToken(token));
        System.out.println("after substring");
        System.out.println(token);
        List<Subject> subjects = subjectsService.getSubjectsBySemester(token,request.getSemesterId());
        
        return ResponseEntity.ok(subjects);
    }

    @PostMapping("/createSubjects")
    public ResponseEntity<Integer> createSubject(@RequestHeader("Authorization")String token,
                                                @RequestBody CreateSubjectsRequest request ){

        if (token.startsWith("Bearer ")) {
            token = token.substring(7);
        }
        System.out.println(JwtUtil.extractEmailFromToken(token));
        System.out.println("after substring");
        System.out.println(token);

        int addResult=subjectsService.addSubject(token,request);
       
        return ResponseEntity.ok(addResult);
    }

    @PutMapping("/alterAttendance")
    public ResponseEntity<Integer> alterAttendance(@RequestHeader("Authorization")String token,
                                                    @RequestBody AlterSubjectsRequest request){

        if (token.startsWith("Bearer ")) {
            token = token.substring(7);
        }
        System.out.println(JwtUtil.extractEmailFromToken(token));
        
        System.out.println("after substring");
        System.out.println(token);
        int alterResult=subjectsService.alterSubject(token, request);                               
        return ResponseEntity.ok(alterResult);
    }

    @PutMapping("/updateSubjectName")
        public ResponseEntity<Integer> updateSubjectName(@RequestHeader("Authorization") String token,
                                                    @RequestBody UpdateSubjectRequest request) {
        if (token.startsWith("Bearer ")) {
            token = token.substring(7);
        }

        System.out.println(JwtUtil.extractEmailFromToken(token));
        int result = subjectsService.updateSubjectName(token, request);
        return ResponseEntity.ok(result);
    }


    @DeleteMapping("/deleteSubject")
    public ResponseEntity<Integer> deleteSubject(@RequestHeader("Authorization")String token,
                                                    @RequestBody AlterSubjectsRequest request){
        
        if (token.startsWith("Bearer ")) {
            token = token.substring(7);
        }
        
        System.out.println(JwtUtil.extractEmailFromToken(token));
        System.out.println(token);
        int deleteResult=subjectsService.deleteSubject(token,request);
        return ResponseEntity.ok(deleteResult);
    }



}
class GetSemesterRequest {
    private Long semesterId;

    public Long getSemesterId() {
        return semesterId;
    }

    public void setSemesterId(Long semesterId) {
        this.semesterId = semesterId;
    }
}

