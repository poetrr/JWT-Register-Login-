package com.AttendanceTracker.Prelag.DTOS;

public class UserCreationResponse {
    private String message;
    private String token;

    public UserCreationResponse(String message, String token) {
        this.message = message;
        this.token = token;
    }

    public String getMessage() {
        return message;
    }

    public String getToken() {
        return token;
    }
    
}
