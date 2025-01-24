package com.AttendanceTracker.Prelag.Model;



import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.*;


    // Getters and Setters
    @Entity
    @Table(name = "AssignedWorks")
    public class AssignedWork {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "assignedworks_id")
        private Integer assignedworksId;
    
        @Column(name = "classID", nullable = false)
        private Integer classId;
    
        @Lob
        @Column(name = "file", nullable = false)
        private byte[] file;
    
        @Column(name = "marks", nullable = false)
        private Integer marks;
    
        @Column(name = "due_date", nullable = false)
        private LocalDateTime dueDate;

        public Integer getAssignedworksId() {
            return assignedworksId;
        }

        public void setAssignedworksId(Integer assignedworksId) {
            this.assignedworksId = assignedworksId;
        }

        public Integer getClassId() {
            return classId;
        }

        public void setClassId(Integer classId) {
            this.classId = classId;
        }

        public byte[] getFile() {
            return file;
        }

        public void setFile(byte[] file) {
            this.file = file;
        }

        public Integer getMarks() {
            return marks;
        }

        public void setMarks(Integer marks) {
            this.marks = marks;
        }

        public LocalDateTime getDueDate() {
            return dueDate;
        }

        public void setDueDate(LocalDateTime dueDate) {
            this.dueDate = dueDate;
        }
    
        // Getters and setters
    }