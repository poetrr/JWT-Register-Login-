package com.AttendanceTracker.Prelag.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.AttendanceTracker.Prelag.Model.User;
import com.AttendanceTracker.Prelag.Repositories.StoreServiceRepository;

@Service
public class StoringService {

    @Autowired
    private StoreServiceRepository store;

     
    public String StoreToDB(String username, String email, String password) {
    	System.out.printf("Username: %s, Email: %s, Password: %s%n", username, email, password);

        if (username == null || email == null || password == null) {
            return "Username, email, or password cannot be null";
        }

        User user = new User();
        
        user.setUsername(username);
        user.setEmailId(email);
        user.setPassword(password);

        try {
            store.save(user);
            return "Stored To DB";
        } catch (Exception e) {
            e.printStackTrace(); // Log the actual error
            return "Error storing user: " + e.getMessage();
        }
       
    }
}
