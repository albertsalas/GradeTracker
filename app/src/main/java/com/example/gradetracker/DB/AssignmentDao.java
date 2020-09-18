package com.example.gradetracker.DB;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.gradetracker.Assignment;

import java.util.List;

@Dao
public interface AssignmentDao {
    @Insert
    void insert(Assignment... assignments);

    @Update
    void update(Assignment... assignments);

    @Delete
    void delete(Assignment... assignments);

    @Query("SELECT * FROM " + AppDatabase.ASSIGNMENT_TABLE + " WHERE assignmentID = :id")
    Assignment getAssignment(int id);

    @Query("SELECT * FROM " + AppDatabase.ASSIGNMENT_TABLE)
    List<Assignment> getAllAssignments();
}
