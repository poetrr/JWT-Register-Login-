package com.AttendanceTracker.Prelag.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;


import com.AttendanceTracker.Prelag.DTOS.SemesterDTO;
import com.AttendanceTracker.Prelag.Model.Semester;
import com.AttendanceTracker.Prelag.Model.User;
import com.AttendanceTracker.Prelag.Repositories.SemesterRepository;
import com.AttendanceTracker.Prelag.Repositories.UserRepository;

public class SemesterService {
    @Autowired
    SemesterRepository semesterRepository;
    @Autowired
    UserRepository userRepository;
    public List<Semester> getSemester(String token,SemesterDTO request){
        String email=JwtUtil.extractEmailFromToken(token);
        if (JwtUtil.validateToken(token, email)==false) {
            System.out.println("Invalid token");
            throw new RuntimeException("Invalid Token"); // ✅ Fixed missing return statement
        } 
        Optional<User> user =userRepository.findByEmail(email);

        if (user.isEmpty()) {
            throw new RuntimeException("User not found");
        }
        int userId = user.get().getId();
        return semesterRepository.findSemestersByUser(userId);
    }

    public int updateSemester(String token,SemesterDTO request){
        String email=JwtUtil.extractEmailFromToken(token);
        if (JwtUtil.validateToken(token, email)==false) {
            System.out.println("Invalid token");
            throw new RuntimeException("Invalid Token"); // ✅ Fixed missing return statement
        } 
        Optional<User> user =userRepository.findByEmail(email);

        if (user.isEmpty()) {
            throw new RuntimeException("User not found");
        }
        int userId = user.get().getId();
        return semesterRepository.updateSemester(request.getSemesterId(),request.getSemesterName(),userId);
        
    }
    
    public int createSemester(String token,SemesterDTO request){
        String email=JwtUtil.extractEmailFromToken(token);
        if (JwtUtil.validateToken(token, email)==false) {
            System.out.println("Invalid token");
            throw new RuntimeException("Invalid Token"); // ✅ Fixed missing return statement
        } 
        Optional<User> user =userRepository.findByEmail(email);

        if (user.isEmpty()) {
            throw new RuntimeException("User not found");
        }
        int userId = user.get().getId();
        return semesterRepository.createSemester(request.getSemesterName(), userId);
    }
    
    public int deleteSemester(String token,SemesterDTO request){
        String email=JwtUtil.extractEmailFromToken(token);
        if (JwtUtil.validateToken(token, email)==false) {
            System.out.println("Invalid token");
            throw new RuntimeException("Invalid Token"); // ✅ Fixed missing return statement
        } 
        Optional<User> user =userRepository.findByEmail(email);

        if (user.isEmpty()) {
            throw new RuntimeException("User not found");
        }
        int userId = user.get().getId();
        return semesterRepository.deleteSemesterByUser(userId, request.getSemesterId());
    }
}
