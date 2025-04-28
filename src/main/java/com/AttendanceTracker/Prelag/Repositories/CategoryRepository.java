package com.AttendanceTracker.Prelag.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.AttendanceTracker.Prelag.Model.Category;

import jakarta.transaction.TransactionScoped;
import jakarta.transaction.Transactional;

public interface CategoryRepository extends JpaRepository<Category,Long>{
    

    //create category
    @Modifying
    @Transactional
    @Query(value="INSERT INTO category(category_name,user_id) "+
                 " VALUES (:categoryName,:userId)",nativeQuery=true)
    int createSemester(@Param("categoryName")String categoryName,
                        @Param("userId")int userId);
    //get categories
    @Query(value="SELECT * FROM category WHERE user_id=:userId",nativeQuery = true)
    List<Category> findCategoryByUser(@Param("userId")Long userId);

    //update category
    @Modifying
    @Transactional
    @Query(value="UPDATE category SET category_name=:categoryName"+
                 "WHERE category_id=:categoryId AND user_id =:userId",nativeQuery = true)
    int UpdateCategory(@Param("categoryId")Long categoryId,
                       @Param("categoryName")Long categoryName,
                       @Param("userId")Long userId);
    //delete category
    @Modifying
    @Transactional
    @Query(value="DELETE FROM category where userId=:userId AND category_id=:categoryId",nativeQuery = true)
    int deleteCategory(@Param("categoryId")Long categoryId,
                       @Param("userId")Long userId);

    

    
}
