package com.example.gradetracker;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class GradeCategoryUnitTest {
    GradeCategory gradeCategory;

    @Before
    public void createGradeCategory() {
        gradeCategory = new GradeCategory("tests", .7, 1, "09/15/2020");
    }

    @Test
    public void constructorTest() {
        assert (gradeCategory != null);
    }

    @Test
    public void gettersAndSettersTest() {
        gradeCategory.setTitle("cat");
        gradeCategory.setWeight(.5);
        gradeCategory.setGradeID(9);
        gradeCategory.setAssignedDate("10/10/2100");

        assertEquals("cat", gradeCategory.getTitle());
        assert (.5 == gradeCategory.getWeight());
        assert (9 == gradeCategory.getGradeID());
        assertEquals("10/10/2100", gradeCategory.getAssignedDate());
    }
}
