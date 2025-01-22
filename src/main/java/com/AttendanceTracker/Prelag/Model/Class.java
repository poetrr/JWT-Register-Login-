package com.AttendanceTracker.Prelag.Model;


import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Classes")
public class Class {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer classId;

    @ManyToOne
    @JoinColumn(name = "creator_id", nullable = false)
    private User creator;

    @Column(nullable = false)
    private String className;

    @Column(nullable = false)
    private String subject;

    @Column(nullable = false, unique = true)
    private String classCode;

    @ManyToMany
    @JoinTable(
        name = "Class_Users",
        joinColumns = @JoinColumn(name = "class_id"),
        inverseJoinColumns = @JoinColumn(name = "class_userID")
    )
    private Set<User> enrolledUsers = new HashSet<>();

    @OneToMany(mappedBy = "classEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<AssignedWork> assignedWorks = new HashSet<>();

    // Getters and Setters
}

