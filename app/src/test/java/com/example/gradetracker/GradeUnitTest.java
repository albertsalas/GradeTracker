package com.example.gradetracker;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class GradeUnitTest {
    Grade grade;

    @Before
    public void createGrade() {
        grade = new Grade(50, 1, 2, 3, "09/04/2020");
    }

    @Test
    public void constructorTest() {
        assert (grade != null);
    }

    @Test
    public void gettersAndSettersTest() {
        grade.setScore(100);
        grade.setAssignmentID(9);
        grade.setStudentID(8);
        grade.setCourseID(7);
        grade.setDateEarned("10/10/2010");

        assert (100 == grade.getScore());
        assert (9 == grade.getAssignmentID());
        assert (8 == grade.getStudentID());
        assert (7 == grade.getCourseID());
        assertEquals("10/10/2010", grade.getDateEarned());
    }
}
