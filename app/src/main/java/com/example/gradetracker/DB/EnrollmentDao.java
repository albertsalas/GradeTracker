package com.example.gradetracker.DB;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.gradetracker.Course;
import com.example.gradetracker.Enrollment;

import java.util.List;


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

    @Query("SELECT * FROM " +AppDatabase.ENROLLMENT_TABLE + " WHERE studentID = :uID")
    List<Enrollment> getStudentsEnrolledClasses(int uID);

    @Query("SELECT * FROM " +AppDatabase.ENROLLMENT_TABLE + " WHERE courseID = :courseID")
    Enrollment getEnrolledClass(int courseID);

    @Query("SELECT * FROM " +AppDatabase.ENROLLMENT_TABLE )
    List<Enrollment> getAllEnrollments();



}
