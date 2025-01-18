package com.AttendanceTracker.Prelag.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.format.annotation.DateTimeFormat;

import com.AttendanceTracker.Prelag.Service.AddAttendanceService;

import java.time.LocalDate;

@RestController
@RequestMapping("/Prelag")
@CrossOrigin(origins = "*")
public class AddAttendanceController {

    @Autowired
    private AddAttendanceService attendanceService;

    @PostMapping("/add")
    public ResponseEntity<String> addAttendance(@RequestBody AddAttendanceRequest attendanceRequest) {
        String response = attendanceService.addAttendance(
                attendanceRequest.getAttendanceId(),
                attendanceRequest.getClassId(),
                attendanceRequest.getUserId(),
                attendanceRequest.getDate().toString(),
                attendanceRequest.getDayHours(),
                attendanceRequest.isPresent()
        );
        return ResponseEntity.ok(response);
    }

    // DTO class as a static inner class
    public static class AddAttendanceRequest {
        private int attendanceId;
        private int classId;
        private long userId;
        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
        private LocalDate date;
        private int dayHours;
        private boolean isPresent;

        // Getters and setters
        public int getAttendanceId() {
            return attendanceId;
        }

        public void setAttendanceId(int attendanceId) {
            this.attendanceId = attendanceId;
        }

        public int getClassId() {
            return classId;
        }

        public void setClassId(int classId) {
            this.classId = classId;
        }

        public long getUserId() {
            return userId;
        }

        public void setUserId(long userId) {
            this.userId = userId;
        }

        public LocalDate getDate() {
            return date;
        }

        public void setDate(LocalDate date) {
            this.date = date;
        }

        public int getDayHours() {
            return dayHours;
        }

        public void setDayHours(int dayHours) {
            this.dayHours = dayHours;
        }

        public boolean isPresent() {
            return isPresent;
        }

        public void setPresent(boolean present) {
            isPresent = present;
        }
    }
}
