package com.example.gradetracker;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.example.gradetracker.DB.AppDatabase;
import com.example.gradetracker.DB.TypeConverters.DateTypeConverter;


/**
 * Represents a student's assignment.
 *
 * @author Albert
 */

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

    /**
     * Creates an Assignment with the specified attributes
     *
     * @param details      the assignment's details
     * @param maxScore     the maximum possible score on the assignment
     * @param earnedScore  the user's earned score
     * @param assignedDate the date the assignment was assigned
     * @param dueDate      the date the assignment was due
     * @param categoryID   used to represent the grading category
     * @param courseId     used to represent the course the assignment belongs to
     */
    public Assignment(String details, double maxScore, double earnedScore, String assignedDate, String dueDate, int categoryID, int courseId) {
        this.details = details;
        this.maxScore = maxScore;
        this.earnedScore = earnedScore;
        this.assignedDate = assignedDate;
        this.dueDate = dueDate;
        this.categoryID = categoryID;
        this.courseId = courseId;
    }

    /**
     * @return the assignments ID
     */
    public int getAssignmentID() {
        return assignmentID;
    }

    /**
     * @param assignmentID new ID for the assignment
     */
    public void setAssignmentID(int assignmentID) {
        this.assignmentID = assignmentID;
    }

    /**
     * @return the assignment's details
     */
    public String getDetails() {
        return details;
    }

    /**
     * @param details the new details for the assignment
     */
    public void setDetails(String details) {
        this.details = details;
    }

    /**
     * @return maximum score for the assignment
     */
    public double getMaxScore() {
        return maxScore;
    }

    /**
     * @param maxScore the new maximum score possible for the assignment
     */
    public void setMaxScore(double maxScore) {
        this.maxScore = maxScore;
    }

    /**
     * @return the user's earned score on the assignment
     */
    public double getEarnedScore() {
        return earnedScore;
    }

    /**
     * @param earnedScore the user's new earned score
     */
    public void setEarnedScore(double earnedScore) {
        this.earnedScore = earnedScore;
    }

    /**
     * @return the assignment's assigned date
     */
    public String getAssignedDate() {
        return assignedDate;
    }

    /**
     * @param assignedDate the new assigned date
     */
    public void setAssignedDate(String assignedDate) {
        this.assignedDate = assignedDate;
    }

    /**
     * @return the assignment's due date
     */
    public String getDueDate() {
        return dueDate;
    }

    /**
     * @param dueDate the assignments new due date
     */
    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    /**
     * @return the ID for what grade category the assignment belongs to
     */
    public int getCategoryID() {
        return categoryID;
    }

    /**
     * @param categoryID the new grade category ID that the assignment belongs to
     */
    public void setCategoryID(int categoryID) {
        this.categoryID = categoryID;
    }

    /**
     * @return the ID for which course the assignment belongs to
     */
    public int getCourseId() {
        return courseId;
    }

    /**
     * @param courseId the new ID for the new course the assignment will belong to
     */
    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }
}
