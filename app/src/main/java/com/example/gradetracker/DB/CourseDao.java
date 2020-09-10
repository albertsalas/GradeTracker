package com.example.gradetracker.DB;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.gradetracker.Course;
import com.example.gradetracker.User;

import java.util.List;

@Dao
public interface CourseDao {
    @Insert
    void insert(Course... courses);

    @Update
    void update(Course... courses);

    @Delete
    void delete(Course... courses);

    @Query("SELECT * FROM " + AppDatabase.COURSE_TABLE + " WHERE courseID = :id")
    Course getCourse(int id);

    @Query("SELECT * FROM " + AppDatabase.COURSE_TABLE)
    List<Course> getAllCourses();
}
