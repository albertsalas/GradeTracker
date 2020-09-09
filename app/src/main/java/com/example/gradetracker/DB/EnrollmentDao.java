package com.example.gradetracker.DB;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.gradetracker.Enrollment;


@Dao
public interface EnrollmentDao {
    @Insert
    void insert(Enrollment... enrollments);

    @Update
    void update(Enrollment... enrollments);

    @Delete
    void delete(Enrollment... enrollments);

    @Query("SELECT * FROM " + AppDatabase.ENROLLMENT_TABLE + " WHERE enrollmentID = :id")
    Enrollment getEnrollment(int id);
}
