package com.AttendanceTracker.Prelag.Repositories;

import com.AttendanceTracker.Prelag.Model.Semester;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

public interface SemesterRepository extends JpaRepository<Semester, Long> {

    // 🔹 1. CREATE (Insert a new semester)
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO semesters (semester_name, user_id) " +
                   "VALUES (:semesterName, :userId)", nativeQuery = true)
    int createSemester(@Param("semesterName") String semesterName,
                       @Param("userId") int userId);

    // 🔹 2. READ (Get all semesters for a user)
    @Query(value = "SELECT * FROM semesters WHERE user_id = :userId", nativeQuery = true)
    List<Semester> findSemestersByUser(@Param("userId") int userId);

    // 🔹 3. READ (Get a semester by ID)
    @Query(value = "SELECT * FROM semesters WHERE semester_id = :semesterId", nativeQuery = true)
    Semester findSemesterById(@Param("semesterId") Long semesterId);

    // 🔹 4. UPDATE (Modify semester name)
    @Modifying
    @Transactional
    @Query(value = "UPDATE semesters SET semester_name = :semesterName " +
                   "WHERE semester_id = :semesterId AND user_id = :userId", nativeQuery = true)
    int updateSemester(@Param("semesterId") Long semesterId,
                       @Param("semesterName") String semesterName,
                       @Param("userId") int userId);



    // 🔹 6. DELETE (Delete a semester ensuring user ownership)
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM semesters " +
                   "WHERE semester_id = :semesterId " +
                   "AND user_id = :userId", nativeQuery = true)
    int deleteSemesterByUser(@Param("userId") int userId,
                             @Param("semesterId") Long semesterId);
}
