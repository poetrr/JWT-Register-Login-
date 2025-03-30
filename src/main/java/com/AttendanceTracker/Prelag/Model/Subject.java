package com.AttendanceTracker.Prelag.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;

@Entity
@Table(name = "Subjects")
public class Subject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="subject_id")
    private Long subjectId;

    @Column(name = "subject_name", nullable = false)
    private String subjectName;

    @Column(name = "present_count", nullable = false)
    private int presentCount;

    @Column(name = "absent_count", nullable = false)
    private int absentCount;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "semester_id", nullable = false)
    private Semester semester;

    // Default Constructor
    public Subject() {
    }

    // Parameterized Constructor
    public Subject(Long subjectId, String subjectName, int presentCount, int absentCount, Semester semester) {
        this.subjectId = subjectId;
        this.subjectName = subjectName;
        this.presentCount = presentCount;
        this.absentCount = absentCount;
        this.semester = semester;
    }

    // Getters and Setters
    public Long getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Long subjectId) {
        this.subjectId = subjectId;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public int getPresentCount() {
        return presentCount;
    }

    public void setPresentCount(int presentCount) {
        this.presentCount = presentCount;
    }

    public int getAbsentCount() {
        return absentCount;
    }

    public void setAbsentCount(int absentCount) {
        this.absentCount = absentCount;
    }

    public Semester getSemester() {
        return semester;
    }

    public void setSemester(Semester semester) {
        this.semester = semester;
    }
}
