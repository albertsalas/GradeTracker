package com.example.gradetracker.DB;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.gradetracker.Grade;

import java.util.List;


@Dao
public interface GradeDao {
    @Insert
    void insert(Grade... grades);

    @Update
    void update(Grade... grades);

    @Delete
    void delete(Grade... grades);

    @Query("SELECT * FROM " + AppDatabase.GRADE_TABLE + " WHERE gradeID = :id")
    Grade getGrade(int id);

    @Query("SELECT * FROM " + AppDatabase.GRADE_TABLE + " WHERE courseID = :cID ")
    List<Grade> getAllGradesbyCourseID(int cID);
}
