package com.example.gradetracker;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.example.gradetracker.DB.AppDatabase;
import com.example.gradetracker.DB.TypeConverters.DateTypeConverter;

import java.util.Date;

@Entity(tableName = AppDatabase.GRADE_CATEGORY_TABLE)
@TypeConverters({DateTypeConverter.class})

public class GradeCategory {
    @PrimaryKey(autoGenerate = true)
    private int categoryID;
    private String title;
    private double weight;
    private int gradeID;
    private Date assignedDate;

    public GradeCategory(String title, double weight, int gradeID, Date assignedDate) {
        this.title = title;
        this.weight = weight;
        this.gradeID = gradeID;
        this.assignedDate = assignedDate;
    }

    public int getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(int categoryID) {
        this.categoryID = categoryID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public int getGradeID() {
        return gradeID;
    }

    public void setGradeID(int gradeID) {
        this.gradeID = gradeID;
    }

    public Date getAssignedDate() {
        return assignedDate;
    }

    public void setAssignedDate(Date assignedDate) {
        this.assignedDate = assignedDate;
    }
}
