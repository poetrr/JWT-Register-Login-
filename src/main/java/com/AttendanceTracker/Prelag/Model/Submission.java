package com.AttendanceTracker.Prelag.Model;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "Submissions")
public class Submission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "submission_id")
    private Integer submissionId;

    @Column(name = "assignedworks_id", nullable = false)
    private Integer assignedworksId;

    @Column(name = "class_userID", nullable = false)
    private Integer classUserId;

    @Lob
    @Column(name = "submitted_file", nullable = false)
    private byte[] submittedFile;

    @Column(name = "marks")
    private Integer marks;

    @Column(name = "isEvaluated", nullable = false)
    private Boolean isEvaluated = false;

    @Column(name = "submission_date", nullable = false)
    private LocalDateTime submissionDate;

    public Integer getSubmissionId() {
        return submissionId;
    }

    public void setSubmissionId(Integer submissionId) {
        this.submissionId = submissionId;
    }

    public Integer getAssignedworksId() {
        return assignedworksId;
    }

    public void setAssignedworksId(Integer assignedworksId) {
        this.assignedworksId = assignedworksId;
    }

    public Integer getClassUserId() {
        return classUserId;
    }

    public void setClassUserId(Integer classUserId) {
        this.classUserId = classUserId;
    }

    public byte[] getSubmittedFile() {
        return submittedFile;
    }

    public void setSubmittedFile(byte[] submittedFile) {
        this.submittedFile = submittedFile;
    }

    public Integer getMarks() {
        return marks;
    }

    public void setMarks(Integer marks) {
        this.marks = marks;
    }

    public Boolean getIsEvaluated() {
        return isEvaluated;
    }

    public void setIsEvaluated(Boolean isEvaluated) {
        this.isEvaluated = isEvaluated;
    }

    public LocalDateTime getSubmissionDate() {
        return submissionDate;
    }

    public void setSubmissionDate(LocalDateTime submissionDate) {
        this.submissionDate = submissionDate;
    }

    // Getters and setters
}

