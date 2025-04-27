package com.AttendanceTracker.Prelag.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.AttendanceTracker.Prelag.Model.User;
import com.AttendanceTracker.Prelag.Repositories.UserRepository;

@Service
public class NotificationService {

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private UserRepository userRepository;

    // Cron expression: second, minute, hour, day of month, month, day(s) of week
    @Scheduled(cron = "0 54 10 * * *")  // Runs at 10:52 AM daily
    public void sendAttendanceReminderToAllUsers() {
        List<User> users = userRepository.findAll();

        for (User user : users) {
            sendEmail(user.getEmailId(), "Attendance Reminder",
                    "Hi " + user.getUsername() + ",\n\nThis is a reminder to update your attendance today.\n\nBest Regards,\nAttendance Tracker");
        }
    }

    private void sendEmail(String to, String subject, String body) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("sidduprofessional012@gmail.com");
        message.setTo(to);
        message.setSubject(subject);
        message.setText(body);
        mailSender.send(message);
    }
}