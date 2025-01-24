package com.AttendanceTracker.Prelag.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.AttendanceTracker.Prelag.Model.Classes;

@Repository
public interface CreateClassRepository extends JpaRepository<Classes,Integer> {

}
