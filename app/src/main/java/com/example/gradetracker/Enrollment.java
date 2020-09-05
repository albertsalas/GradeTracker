package com.example.gradetracker;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.gradetracker.DB.AppDatabase;

import java.util.Date;

@Entity(tableName = AppDatabase.ENROLLMENT_TABLE)
public class Enrollment {
    private int studentID;
    private int courseID;
    private Date enrollmentDate;

    public Enrollment(int studentID, int courseID, Date enrollmentDate) {
        this.studentID = studentID;
        this.courseID = courseID;
        this.enrollmentDate = enrollmentDate;
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

    public Date getEnrollmentDate() {
        return enrollmentDate;
    }

    public void setEnrollmentDate(Date enrollmentDate) {
        this.enrollmentDate = enrollmentDate;
    }
}
