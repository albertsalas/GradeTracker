package com.example.gradetracker;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.example.gradetracker.DB.AppDatabase;
import com.example.gradetracker.DB.TypeConverters.DateTypeConverter;

import java.util.Date;

@Entity(tableName = AppDatabase.COURSE_TABLE)
@TypeConverters({DateTypeConverter.class})

public class Course {

    @PrimaryKey(autoGenerate = true)
    private int courseID;
    private String instructor;
    private String title;
    private String description;
    private Date startDate;
    private Date endDate;

    public Course(String instructor, String title, String description, Date startDate, Date endDate) {
        this.instructor = instructor;
        this.title = title;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public int getCourseID() {
        return courseID;
    }

    public void setCourseID(int courseID) {
        this.courseID = courseID;
    }

    public String getInstructor() {
        return instructor;
    }

    public void setInstructor(String instructor) {
        this.instructor = instructor;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}
