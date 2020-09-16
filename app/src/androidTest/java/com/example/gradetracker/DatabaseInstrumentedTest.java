package com.example.gradetracker;

import android.content.Context;

import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.example.gradetracker.DB.AppDatabase;
import com.example.gradetracker.DB.AssignmentDao;
import com.example.gradetracker.DB.CourseDao;
import com.example.gradetracker.DB.EnrollmentDao;
import com.example.gradetracker.DB.GradeCategoryDao;
import com.example.gradetracker.DB.GradeDao;
import com.example.gradetracker.DB.UserDao;
import com.example.gradetracker.User;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;

import static org.junit.Assert.*;

@RunWith(AndroidJUnit4.class)
public class DatabaseInstrumentedTest {
    private AppDatabase db;
    private AssignmentDao assignmentDao;
    private CourseDao courseDao;
    private EnrollmentDao enrollmentDao;
    private GradeCategoryDao gradeCategoryDao;
    private GradeDao gradeDao;
    private UserDao userDao;
    private String date;
    private Assignment assignment;
    private Course course;
    private Enrollment enrollment;
    private GradeCategory gradeCategory;
    private Grade grade;
    private User user;

    @Before
    public void createDb() {
        Context context = ApplicationProvider.getApplicationContext();
        db = Room.inMemoryDatabaseBuilder(context, AppDatabase.class).build();
        // DAOs
        assignmentDao = db.getAssignmentDao();
        courseDao = db.getCourseDao();
        enrollmentDao = db.getEnrollmentDao();
        gradeCategoryDao = db.getGradeCategoryDao();
        gradeDao = db.getGradeDao();
        userDao = db.getUserDao();

        // instantiate some objects for testing
        date = "09/15/2020";
        user = new User("bob123", "dog", "bob", "bobson");
        course = new Course("bob", "438", "software engineering", date, date);
        // set gradeID to -1 because a grade needs to be created first
        gradeCategory = new GradeCategory("quizzes", .5, -1, date);
        assignment = new Assignment("test", 100, 99, date, date,
                gradeCategory.getCategoryID(), course.getCourseID());
        grade = new Grade(99, assignment.getAssignmentID(), user.getUserID(), course.getCourseID(), date);
        // now after the grade object is created, its ID can be used in gradeCategory
        gradeCategory.setGradeID(grade.getGradeID());
        enrollment = new Enrollment(user.getUserID(), course.getCourseID());
    }

    @After
    public void closeDb() throws IOException {
        db.close();
    }

    @Test
    public void writeAssignmentAndRead() throws Exception {
        assignmentDao.insert(assignment);
        Assignment returnedAssignment = assignmentDao.getAssignment(assignment.getAssignmentID());
        assert (assignment.equals(returnedAssignment));
    }

    @Test
    public void writeCourseAndRead() throws Exception {
        courseDao.insert(course);
        Course returnedCourse = courseDao.getCourse(course.getCourseID());
        assert (course.equals(returnedCourse));
    }

    @Test
    public void writeEnrollmentAndRead() throws Exception {
        enrollmentDao.insert(enrollment);
        Enrollment returnedEnrollment = enrollmentDao.getEnrollment(enrollment.getEnrollmentID());
        assert (enrollment.equals(returnedEnrollment));
    }

    @Test
    public void writeGradeCategoryAndRead() throws Exception {
        gradeCategoryDao.insert(gradeCategory);
        GradeCategory returnedGradeCategory = gradeCategoryDao.getGradeCategory(gradeCategory.getCategoryID());
        assert (gradeCategory.equals(returnedGradeCategory));
    }

    @Test
    public void writeGradeAndRead() throws Exception {
        gradeDao.insert(grade);
        Grade returnedGrade = gradeDao.getGrade(grade.getGradeID());
        assert (grade.equals(returnedGrade));
    }

    @Test
    public void writeUserAndRead() throws Exception {
        userDao.insert(user);
        User returnedUser = userDao.getUser(user.getUserID());
        assert (user.equals(returnedUser));
    }
}
