package com.AttendanceTracker.Prelag.Model;


import jakarta.persistence.*;

@Entity
@Table(name = "Classes")
public class Classes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "class_id")
    private Integer classId;

    @Column(name = "creator_id", nullable = false)
    private Integer creatorId;

    @Column(name = "className", nullable = false)
    private String className;

    @Column(name = "subject", nullable = false)
    private String subject;

    @Column(name = "classCode", nullable = false, unique = true)
    private String classCode;

    public Integer getClassId() {
        return classId;
    }

    public void setClassId(Integer classId) {
        this.classId = classId;
    }

    public Integer getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(Integer creatorId) {
        this.creatorId = creatorId;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getClassCode() {
        return classCode;
    }

    public void setClassCode(String classCode) {
        this.classCode = classCode;
    }

    // Getters and setters
}
