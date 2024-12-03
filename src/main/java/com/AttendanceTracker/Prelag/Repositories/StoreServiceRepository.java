package com.AttendanceTracker.Prelag.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.AttendanceTracker.Prelag.Model.User;

@Repository
public interface StoreServiceRepository extends JpaRepository<User, Long> {
    // Additional query methods can be defined here if needed
}
