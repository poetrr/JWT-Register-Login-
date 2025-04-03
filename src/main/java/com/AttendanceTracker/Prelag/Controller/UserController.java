package com.AttendanceTracker.Prelag.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.AttendanceTracker.Prelag.DTOS.SemesterDTO;
import com.AttendanceTracker.Prelag.Service.JwtUtil;

import com.AttendanceTracker.Prelag.Service.UserService;
@RestController
@RequestMapping("/classroom")
public class UserController {

    @Autowired
    UserService userService;

   @DeleteMapping("/deleteUser")
    public ResponseEntity<Integer> deleteSemester(@RequestHeader("Authorization")String token,
                                                @RequestBody SemesterDTO request ){

        if (token.startsWith("Bearer ")) {
            token = token.substring(7);
        }
        System.out.println(JwtUtil.extractEmailFromToken(token));
        System.out.println("after substring");
        System.out.println(token);

        int addResult=userService.deleteUserAndAssociatedData(token);
       
        return ResponseEntity.ok(addResult);
    }

    
}
