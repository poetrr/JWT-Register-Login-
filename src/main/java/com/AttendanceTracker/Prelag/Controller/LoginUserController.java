package com.AttendanceTracker.Prelag.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.AttendanceTracker.Prelag.Service.HashService;
import com.AttendanceTracker.Prelag.Service.JwtUtil;
import com.AttendanceTracker.Prelag.Service.LoginService;


@RestController
@RequestMapping(path="/classroom")
@CrossOrigin(origins="*")//Allow all origins
public class LoginUserController {
    
    @Autowired
    private LoginService loginService;
    
    @Autowired
    private HashService hashService;
    
    
    @PostMapping(path="/login")
    public ResponseEntity<String> loginUser(@RequestBody LoginDTO log){
        String email=log.getEmail();
        String password=log.getPassword();
        String storedHashedPassword = loginService.getHashedPasswordByEmail(email);
        System.out.println("Entered password"+password+"Stored Password"+storedHashedPassword);
        if(storedHashedPassword==null) {
        	return ResponseEntity.badRequest().body("user not found please sign up");
        }
        boolean isValid=hashService.verifyPassword(password, storedHashedPassword);
        if(!isValid) {
        	return ResponseEntity.status(401).body("Invalid credential");
        }
        
        String token = JwtUtil.generateToken(email);
        String tokenEmail=JwtUtil.extractEmailFromToken(token);
        System.out.println(tokenEmail);
        return ResponseEntity.ok(token);
    }
 
}

class LoginDTO{
    private String email;
    private String password;
    
    public void setEmail(String email){
        this.email=email;
    }
    public String getEmail(){
        return email;
    }
    public void setPassword(String password){
        this.password=password;
    }
    public String getPassword(){
        return password;
    }
}
