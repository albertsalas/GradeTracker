package com.example.gradetracker;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

class CourseUnitTest {
    Course course;

    @Before
    public void createCourse() {
        course = new Course("Dr. C", "CST 438", "software engineering",
                "08/30/2020", "12/10/2020");
    }

    @Test
    public void constructorTest() {
        assert(course != null);
    }

    @Test
    public void gettersAndSettersTest() {
        course.setInstructor("Doom Slayer");
        course.setTitle("Killing Demons 101");
        course.setDescription("Becoming a better sentinel");
        course.setStartDate("66/66/6666");
        course.setEndDate("99/99/9999");

        assertEquals("Doom Slayer", course.getInstructor());
        assertEquals("Killing Demons 101", course.getTitle());
        assertEquals("Becoming a better sentinel", course.getDescription());
        assertEquals("66/66/6666", course.getStartDate());
        assertEquals("99/99/9999", course.getEndDate());
    }
}
