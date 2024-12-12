package com.AttendanceTracker.Prelag.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.AttendanceTracker.Prelag.Model.User;

@Repository
public interface LoginUserRepository extends JpaRepository<User,Long> {
	User findByEmail(String email);
}
