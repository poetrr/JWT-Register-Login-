package com.AttendanceTracker.Prelag.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.AttendanceTracker.Prelag.Model.Semester;
import com.AttendanceTracker.Prelag.Model.User;
import com.AttendanceTracker.Prelag.Repositories.SemesterRepository;

import com.AttendanceTracker.Prelag.Repositories.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SemesterRepository semesterRepository;

    public int  deleteUserAndAssociatedData(String token) {
        String email=JwtUtil.extractEmailFromToken(token);
        if (JwtUtil.validateToken(token, email)==false) {
            System.out.println("Invalid token");
            throw new RuntimeException("Invalid Token"); 
        } 
        Optional<User> user =userRepository.findByEmail(email);

        if (user.isEmpty()) {
            throw new RuntimeException("User not found");
        }
        int userId = user.get().getId();
        // 🔹 Step 1: Fetch all semesters for the user
        List<Semester> semesters = semesterRepository.findSemestersByUserId(userId);

        for (Semester semester : semesters) {
            Long semesterId = semester.getSemesterId();

            // 🔹 Step 2: Delete all subjects linked to this semester
            semesterRepository.deleteSubjectsBySemester(semesterId);

            // 🔹 Step 3: Delete the semester
            semesterRepository.deleteSemesterByUser(userId, semesterId);
        }

        // 🔹 Step 4: Delete the user
          return userRepository.deleteById(userId);
    }
}
