package com.example.gradetracker;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class EnrollmentUnitTest {
    Enrollment enrollment;

    @Before
    public void createEnrollment() {
        enrollment = new Enrollment(100, 200);
    }

    @Test
    public void constructorTest() {
        assert (enrollment != null);
    }

    @Test
    public void gettersAndSettersTest() {
        enrollment.setStudentID(3);
        enrollment.setCourseID(5);

        assert (3 == enrollment.getStudentID());
        assert (5 == enrollment.getCourseID());
    }
}
