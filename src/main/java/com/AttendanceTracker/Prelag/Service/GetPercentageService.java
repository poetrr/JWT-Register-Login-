package com.AttendanceTracker.Prelag.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class GetPercentageService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // Method to get the classes for which the user has attendance, including class name
    public Map<Integer, String> getClassesByUserId(Integer userId) {
        String sql = "SELECT DISTINCT a.class_id, c.class_name " +
                     "FROM attendance a " +
                     "JOIN classes c ON a.class_id = c.class_id " +
                     "WHERE a.user_id = ?";
        
        List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql, userId);
        Map<Integer, String> classNames = new HashMap<>();
        
        // Iterate over the result and map class_id to class_name
        for (Map<String, Object> row : rows) {
            Integer classId = (Integer) row.get("class_id");
            String className = (String) row.get("class_name");
            classNames.put(classId, className);
        }
        
        return classNames;
    }

    // Method to calculate attendance percentage for a user in a specific class
    public Double calculateAttendancePercentage(Integer classId, Integer userId) {
        String sql = "SELECT (SUM(CASE WHEN is_present = TRUE THEN day_hours ELSE 0 END) / SUM(day_hours)) * 100 " +
                     "AS attendancePercentage FROM attendance WHERE class_id = ? AND user_id = ?";
        return jdbcTemplate.queryForObject(sql, Double.class, classId, userId);
    }

    // Method to get attendance percentage for all classes
    public Map<String, Double> getAttendancePercentagesForUser(Integer userId) {
        Map<String, Double> attendancePercentages = new HashMap<>();

        // Get the classes the user has attendance for, with class names
        Map<Integer, String> classNames = getClassesByUserId(userId);

        // Calculate attendance percentage for each class
        for (Map.Entry<Integer, String> entry : classNames.entrySet()) {
            Integer classId = entry.getKey();
            String className = entry.getValue();
            Double percentage = calculateAttendancePercentage(classId, userId);
            attendancePercentages.put(className, percentage);
        }

        return attendancePercentages;
    }
}