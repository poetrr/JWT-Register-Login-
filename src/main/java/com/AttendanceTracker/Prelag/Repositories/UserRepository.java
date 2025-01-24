package com.AttendanceTracker.Prelag.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import com.AttendanceTracker.Prelag.Model.User;
@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
    User findByEmail(String email);

}
