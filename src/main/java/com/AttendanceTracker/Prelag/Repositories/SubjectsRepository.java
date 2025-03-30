
package com.AttendanceTracker.Prelag.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.AttendanceTracker.Prelag.Model.Subject;

@Repository
public interface SubjectsRepository extends JpaRepository<Subject, Long> {


    @Modifying
    @Transactional
    @Query(value = "DELETE s FROM subjects s " +
                   "JOIN semesters sem ON s.semester_id = sem.semester_id " +
                   "JOIN users u ON sem.user_id = u.user_id " +
                   "WHERE s.subject_id = :subjectId " +
                   "AND sem.semester_id = :semesterId " +
                   "AND u.user_id = :userId",
                   nativeQuery = true)
    int deleteSubject(@Param("userId") int userId,
                                  @Param("semesterId") Long semesterId,
                                  @Param("subjectId") Long subjectId);


    @Query(value = "SELECT s.* FROM subjects s " +
                   "JOIN semesters sem ON s.semester_id = sem.semester_id " +
                   "JOIN users u ON sem.user_id = u.user_id " +
                   "WHERE s.semester_id = :semesterId AND u.user_id = :userId", 
           nativeQuery = true)
    List<Subject> findSubjectsBySemesterIdAndUserId(@Param("semesterId") Long semesterId, 
                                                    @Param("userId") int userId);



    @Modifying
    @Transactional
    @Query(value="INSERT INTO subjects (subject_name, semester_id, present_count, absent_count) " +
                 "SELECT :subjectName, sem.semester_id, :presentCount, :absentCount " +
                 "FROM semesters sem " +
                 "JOIN users u ON sem.user_id = u.user_id " +
                 "WHERE sem.semester_id = :semesterId AND u.user_id = :userId",nativeQuery = true)
    int createSubject(@Param("userId")int userId,
                         @Param("semesterId")Long semesterId,
                        
                         @Param("subjectName")String subjectName,
                         @Param("presentCount")int presentcount,
                         @Param("absentCount")int absentcount);

    
    @Modifying
    @Transactional
    @Query(value = "UPDATE subjects " +
               "SET present_count = :presentCount, absent_count = :absentCount " +
               "WHERE subject_id = :subjectId " +
               "AND semester_id = :semesterId",
               nativeQuery = true)
    int alterAttendance(@Param("semesterId") Long semesterId,
                    @Param("subjectId") Long subjectId,
                    @Param("presentCount") int presentCount,
                    @Param("absentCount") int absentCount);

                         
                         
    

}
