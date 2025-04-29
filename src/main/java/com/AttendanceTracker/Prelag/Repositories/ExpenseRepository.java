package com.AttendanceTracker.Prelag.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;


import com.AttendanceTracker.Prelag.Model.Expense;


public interface ExpenseRepository extends JpaRepository<Expense,Long>{

   

    

}
