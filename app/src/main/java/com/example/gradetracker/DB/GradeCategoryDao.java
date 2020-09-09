package com.example.gradetracker.DB;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.gradetracker.GradeCategory;


@Dao
public interface GradeCategoryDao {
    @Insert
    void insert(GradeCategory... gradeCategories);

    @Update
    void update(GradeCategory... gradeCategories);

    @Delete
    void delete(GradeCategory... gradeCategories);

    @Query("SELECT * FROM " + AppDatabase.GRADE_CATEGORY_TABLE + " WHERE categoryID = :id")
    GradeCategory getGradeCategory(int id);
}
