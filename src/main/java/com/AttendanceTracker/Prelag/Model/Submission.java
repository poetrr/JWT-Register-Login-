package com.AttendanceTracker.Prelag.Model;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "Submissions")
public class Submission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer submissionId;

    @ManyToOne
    @JoinColumn(name = "assignedworks_id", nullable = false)
    private AssignedWork assignedWork;

    @ManyToOne
    @JoinColumn(name = "class_userID", nullable = false)
    private User user;

    @Lob
    @Column(nullable = false)
    private byte[] submittedFile;

    private Integer marks;

    @Column(nullable = false)
    private Boolean isEvaluated = false;

    @Column(nullable = false)
    private LocalDateTime submissionDate;

    // Getters and Setters
}

