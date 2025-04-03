package com.AttendanceTracker.Prelag.Repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import com.AttendanceTracker.Prelag.Model.User;

import jakarta.transaction.Transactional;
@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
    Optional<User> findByEmail(String email);

    @Modifying
    @Transactional
    @Query("UPDATE User u SET u.username = :userName WHERE u.id = :userId")
    int changeUserName(@Param("userName") String userName, @Param("userId") int userId);

    

    int deleteById(int userId);
    
}
