package com.example.gradetracker;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.example.gradetracker.DB.AppDatabase;
import com.example.gradetracker.DB.TypeConverters.DateTypeConverter;

import java.util.Date;

/**
 * Class for interacting with the assignment database
 * @author Albert
 * @author Ben
 * @author Ozzie
 * @version 1.0
 */
@Entity(tableName = AppDatabase.ASSIGNMENT_TABLE)
@TypeConverters({DateTypeConverter.class})
public class Assignment {
    @PrimaryKey(autoGenerate = true)
    private int assignmentID;
    private String details;
    private double maxScore;
    private double earnedScore;
    private String assignedDate;
    private String dueDate;
    private int categoryID;
    private int courseId;

    public Assignment(String details, double maxScore, double earnedScore, String assignedDate, String dueDate, int categoryID, int courseId) {
        this.details = details;
        this.maxScore = maxScore;
        this.earnedScore = earnedScore;
        this.assignedDate = assignedDate;
        this.dueDate = dueDate;
        this.categoryID = categoryID;
        this.courseId = courseId;
    }

    public int getAssignmentID() {
        return assignmentID;
    }

    public void setAssignmentID(int assignmentID) {
        this.assignmentID = assignmentID;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public double getMaxScore() {
        return maxScore;
    }

    public void setMaxScore(double maxScore) {
        this.maxScore = maxScore;
    }

    public double getEarnedScore() {
        return earnedScore;
    }

    public void setEarnedScore(double earnedScore) {
        this.earnedScore = earnedScore;
    }

    public String getAssignedDate() {
        return assignedDate;
    }

    public void setAssignedDate(String assignedDate) {
        this.assignedDate = assignedDate;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public int getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(int categoryID) {
        this.categoryID = categoryID;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }
}
