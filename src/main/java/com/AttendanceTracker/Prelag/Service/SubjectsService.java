package com.AttendanceTracker.Prelag.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.AttendanceTracker.Prelag.DTOS.AlterSubjectsRequest;
import com.AttendanceTracker.Prelag.DTOS.CreateSubjectsRequest;
import com.AttendanceTracker.Prelag.Model.Subject;
import com.AttendanceTracker.Prelag.Model.User;
import com.AttendanceTracker.Prelag.Repositories.SubjectsRepository;
import com.AttendanceTracker.Prelag.Repositories.UserRepository;

@Service
public class SubjectsService {
    @Autowired
    private SubjectsRepository subjectsRepository;
    @Autowired
    private UserRepository userRepository;

    public List<Subject>getSubjectsBySemester(String token,Long semesterId){


        System.out.println("entered into service");
        String email=JwtUtil.extractEmailFromToken(token);

        System.out.println(email);
        if (JwtUtil.validateToken(token, email)==false) {
            System.out.println("Invalid token");
            throw new RuntimeException("Invalid Token"); // ✅ Fixed missing return statement
        } 
        System.out.println("validation");  
        Optional<User> user =userRepository.findByEmail(email);

        if (user.isEmpty()) {
            throw new RuntimeException("User not found");
        }
        int userId = user.get().getId();
        System.out.println(userId);
        // Fetch subjects mapped to the semester of the user
        return subjectsRepository.findSubjectsBySemesterIdAndUserId(semesterId, userId);  
    }

    public  int addSubject(String token, CreateSubjectsRequest request){
        System.out.println("entered into service");
        String email=JwtUtil.extractEmailFromToken(token);

        System.out.println(email);
        if (JwtUtil.validateToken(token, email)==false) {
            System.out.println("Invalid token");
            throw new RuntimeException("Invalid Token"); // ✅ Fixed missing return statement
        } 
        System.out.println("validation");  
        Optional<User> user =userRepository.findByEmail(email);

        if (user.isEmpty()) {
            throw new RuntimeException("User not found");
        }
        int userId = user.get().getId();
        System.out.println(userId);

        return subjectsRepository.createSubject(userId,
                                                request.getSemesterId(), 
                                                request.getSubjectName(), 
                                                request.getPresentCount(),
                                                request.getAbsentCount());
      
    }

    public int alterSubject(String token,AlterSubjectsRequest request){
        System.out.println("entered into service");
        String email=JwtUtil.extractEmailFromToken(token);

        System.out.println(email);
        if (JwtUtil.validateToken(token, email)==false) {
            System.out.println("Invalid token");
            throw new RuntimeException("Invalid Token"); // ✅ Fixed missing return statement
        } 
        System.out.println("validation");  
        Optional<User> user =userRepository.findByEmail(email);

        if (user.isEmpty()) {
            throw new RuntimeException("User not found");
        }
        int userId = user.get().getId();
        System.out.println(userId);
        System.out.println(request.getAbsentCount());
        System.out.println(request.getPresentCount());
        return subjectsRepository.alterAttendance(
                                                request.getSemesterId(),
                                                request.getSubjectId(),
                                                request.getPresentCount(),
                                                request.getAbsentCount());
    }

    public int deleteSubject(String token,AlterSubjectsRequest request){
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

        // Use custom delete query that verifies user ownership
        int deletedRows = subjectsRepository.deleteSubject(userId, request.getSemesterId(), request.getSubjectId());
        
        if (deletedRows == 0) {
            throw new RuntimeException("Subject not found or does not belong to user");
        }
        
        return deletedRows;
    }


}
