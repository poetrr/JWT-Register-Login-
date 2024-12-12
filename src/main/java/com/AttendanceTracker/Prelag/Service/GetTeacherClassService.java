package com.AttendanceTracker.Prelag.Service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class GetTeacherClassService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public boolean validateToken(String token) {
        return JwtUtil.validateToken(token);
    }

    public String extractEmailFromToken(String token) {
        return JwtUtil.extractEmailFromToken(token);
    }
    
    public Integer getUserIdByEmail(String email) {
        String sql = "SELECT user_id FROM users WHERE email = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{email}, Integer.class);
    }

    public List<Map<String, Object>> getClassesByUserId(Integer userId) {
        String sql = "SELECT class_name, created_at FROM classes WHERE creator_id = ?";
        return jdbcTemplate.queryForList(sql, userId);
    }
}