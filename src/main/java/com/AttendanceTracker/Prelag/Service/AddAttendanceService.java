package com.AttendanceTracker.Prelag.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
@Service
public class AddAttendanceService {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	public boolean validateToken(String token) {
		return JwtUtil.validateToken(token);
	}
	public String extractEmailFromToken(String token) {
		return JwtUtil.extractEmailFromToken(token);
	}
	public Integer getUserByEmail(String email) {
		String sql = "SELECT user_id FROM users WHERE email = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{email}, Integer.class);
	}
	public String addAttendance(int attendanceId, int classId, long userId, String date, int dayHours, boolean isPresent) {
		String updateQuery = "UPDATE attendance " +
                "SET class_id = ?, user_id = ?, date = ?, day_hours = ?, is_present = ? " +
                "WHERE attendance_id = ?";
		try {
			int rowsUpdated=jdbcTemplate.update(updateQuery,classId,userId,date,dayHours,isPresent,attendanceId);
			if(rowsUpdated>0) {
				return "Attendance added successfully";
			}
			else {
				return "No user found";
			}
		}catch(Exception e) {
			e.printStackTrace();
			return "Error occurred.Please try later!";
		}
	}
}
