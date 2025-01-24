package com.AttendanceTracker.Prelag.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.AttendanceTracker.Prelag.Model.Classes;
import com.AttendanceTracker.Prelag.Model.User;
import com.AttendanceTracker.Prelag.Repositories.CreateClassRepository;
import com.AttendanceTracker.Prelag.Repositories.UserRepository;

public class CreateClassService {
    @Autowired
    private UserRepository userRepository;
    private CreateClassRepository createClassRepository;
    @Autowired
    private Classes classvariable;
    public User getUserByEmail(String email){
        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException("User not found with email: " + email);
        }
        return user;
    }

    public boolean SaveClass(int creatorId,String className,String subjectName){
        try{
            classvariable.setCreatorId(creatorId);
            classvariable.setClassName(className);
            classvariable.setSubject(subjectName);
            createClassRepository.save(classvariable);
            return true;
        }catch(Exception e){
            return false;
        }

    }
    
        
        
    
}
