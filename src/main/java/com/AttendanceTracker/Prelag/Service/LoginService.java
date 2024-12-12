package com.AttendanceTracker.Prelag.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.AttendanceTracker.Prelag.Model.User;
import com.AttendanceTracker.Prelag.Repositories.LoginUserRepository;

@Service
public class LoginService {
	@Autowired
    private LoginUserRepository loginRepository;

    // Fetch the hashed password by email
    public String getHashedPasswordByEmail(String email) {
        User user = loginRepository.findByEmail(email);
        return (user != null) ? user.getPassword() : null;
    }
}
