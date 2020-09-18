package com.example.gradetracker;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.example.gradetracker.DB.AppDatabase;
import com.example.gradetracker.DB.TypeConverters.DateTypeConverter;

import java.util.Date;

/**
 * Class for accessing and manipulating the Grade database
 * @author Albert
 * @author Ben
 * @author Ozzie
 * @version 1.0
 */
@Entity(tableName = AppDatabase.GRADE_TABLE)
@TypeConverters({DateTypeConverter.class})
public class Grade {
    @PrimaryKey(autoGenerate = true)
    private int gradeID;
    private double score;
    private int assignmentID;
    private int studentID;
    private int courseID;
    private String dateEarned;

    public Grade(double score, int assignmentID, int studentID, int courseID, String dateEarned) {
        this.score = score;
        this.assignmentID = assignmentID;
        this.studentID = studentID;
        this.courseID = courseID;
        this.dateEarned = dateEarned;
    }

    public int getGradeID() {
        return gradeID;
    }

    public void setGradeID(int gradeID) {
        this.gradeID = gradeID;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public int getAssignmentID() {
        return assignmentID;
    }

    public void setAssignmentID(int assignmentID) {
        this.assignmentID = assignmentID;
    }

    public int getStudentID() {
        return studentID;
    }

    public void setStudentID(int studentID) {
        this.studentID = studentID;
    }

    public int getCourseID() {
        return courseID;
    }

    public void setCourseID(int courseID) {
        this.courseID = courseID;
    }

    public String getDateEarned() {
        return dateEarned;
    }

    public void setDateEarned(String dateEarned) {
        this.dateEarned = dateEarned;
    }
}
