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


import com.AttendanceTracker.Prelag.DTOS.SemesterDTO;
import com.AttendanceTracker.Prelag.Model.Semester;
import com.AttendanceTracker.Prelag.Service.JwtUtil;
import com.AttendanceTracker.Prelag.Service.SemesterService;

@RestController
@RequestMapping("/classroom")
@CrossOrigin(origins="*")
public class SemesterController {
    @Autowired
    private SemesterService semesterService;

    @GetMapping("/getSemester")
    public ResponseEntity<List<Semester>> getSemesters(
                @RequestHeader("Authoriztion")String token,
                @RequestBody SemesterDTO request){
        if (token.startsWith("Bearer ")) {
            token = token.substring(7);
        }
        System.out.println(JwtUtil.extractEmailFromToken(token));
        System.out.println("after substring");
        System.out.println(token);

        List<Semester> semesters=semesterService.getSemester(token, request);
        return ResponseEntity.ok(semesters);
    }
    
    @PostMapping("/createSemester")
    public ResponseEntity<Integer> createSemester(@RequestHeader("Authorization")String token,
                                                @RequestBody SemesterDTO request ){

        if (token.startsWith("Bearer ")) {
            token = token.substring(7);
        }
        System.out.println(JwtUtil.extractEmailFromToken(token));
        System.out.println("after substring");
        System.out.println(token);

        int addResult=semesterService.createSemester(token,request);
       
        return ResponseEntity.ok(addResult);
    }

    @PutMapping("/alterSemester")
    public ResponseEntity<Integer> updateSemester(@RequestHeader("Authorization")String token,
                                                @RequestBody SemesterDTO request ){

        if (token.startsWith("Bearer ")) {
            token = token.substring(7);
        }
        System.out.println(JwtUtil.extractEmailFromToken(token));
        System.out.println("after substring");
        System.out.println(token);

        int addResult=semesterService.updateSemester(token,request);
       
        return ResponseEntity.ok(addResult);
    }
    
    @DeleteMapping("/deleteMapping")
    public ResponseEntity<Integer> deleteSemester(@RequestHeader("Authorization")String token,
                                                @RequestBody SemesterDTO request ){

        if (token.startsWith("Bearer ")) {
            token = token.substring(7);
        }
        System.out.println(JwtUtil.extractEmailFromToken(token));
        System.out.println("after substring");
        System.out.println(token);

        int addResult=semesterService.deleteSemester(token,request);
       
        return ResponseEntity.ok(addResult);
    }
}
