package com.AttendanceTracker.Prelag.Model;



import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.*;

@Entity
@Table(name = "AssignedWorks")
public class AssignedWork {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer assignedworksId;

    @ManyToOne
    @JoinColumn(name = "classID", nullable = false)
    private Class classEntity;

    @Lob
    @Column(nullable = false)
    private byte[] file;

    @Column(nullable = false)
    private Integer marks;

    @Column(nullable = false)
    private LocalDateTime dueDate;

    @OneToMany(mappedBy = "assignedWork", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Submission> submissions;

    // Getters and Setters
}
