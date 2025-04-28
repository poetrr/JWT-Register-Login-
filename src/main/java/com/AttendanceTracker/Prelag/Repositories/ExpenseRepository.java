package com.AttendanceTracker.Prelag.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.AttendanceTracker.Prelag.Model.Expense;

import jakarta.transaction.Transactional;

public interface ExpenseRepository extends JpaRepository<Expense,Long>{

    //create Expense
   @Modifying
   @Transactional
   @Query(value="",nativeQuery=true)
   int createExpense(@Param("userId")Long userId,
                     @Param("categoryId")Long categoryId,
                     @Param("expenseName")String expenseName,
                     @Param("amount")double amount);
                     
   @Modifying
   @Transactional
   @Query(value="",nativeQuery=true)
   int createExpense(@Param("userId")Long userId,
                     @Param("categoryId")Long categoryId,
                     @Param("expenseName")String expenseName,
                     @Param("amount")double amount,
                     @Param("description")String description);

    @Modifying
    @Transactional
    @Query(value="",nativeQuery = true)
    int deleteExpense(@Param("userId")Long userId,
                      @Param("categoryId")Long categoryId,
                      @Param("expenseId")Long expenseId);


    @Modifying
    @Transactional
    @Query(value="",nativeQuery = true)
    int updateExpense(@Param("userId")Long userId,
                      @Param("categoryId")Long categoryId,
                      @Param("expenseId")Long expenseId,
                      @Param("expenseName")Long expenseName,
                      @Param("amount")Long amount);

    @Modifying
    @Transactional
    @Query(value="",nativeQuery = true)
    int updateExpense(@Param("userId")Long userId,
                      @Param("categoryId")Long categoryId,
                      @Param("expenseId")Long expenseId,
                      @Param("expenseName")Long expenseName,
                      @Param("amount")Long amount,
                      @Param("description")String description);

    

}
