package com.example.gradetracker;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class AssignmentUnitTest {
    Assignment assignment;

    @Before
    public void createAssignment() {
        assignment = new Assignment("details", 100, 99,
                "09/15/2020", "09/18/2020", 0, -1);
    }

    @Test
    public void constructorTest() {
        assert (assignment != null);
    }

    @Test
    public void gettersAndSettersTest() {
        assignment.setDetails("test");
        assignment.setMaxScore(50);
        assignment.setEarnedScore(49);
        assignment.setAssignedDate("01/01/1900");
        assignment.setDueDate("12/21/9999");
        assignment.setCategoryID(1);
        assignment.setCourseId(5);

        assertEquals("test", assignment.getDetails());
        assert (50 == assignment.getMaxScore());
        assert (49 == assignment.getEarnedScore());
        assertEquals("01/01/1900", assignment.getAssignedDate());
        assertEquals("12/21/9999", assignment.getDueDate());
        assert (1 == assignment.getCategoryID());
        assert (5 == assignment.getCourseId());
    }
}
