package com.example.gradetracker.DB;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.gradetracker.Assignment;
import com.example.gradetracker.Course;
import com.example.gradetracker.Enrollment;
import com.example.gradetracker.Grade;
import com.example.gradetracker.GradeCategory;
import com.example.gradetracker.User;

@Database(entities = {User.class, Course.class, GradeCategory.class, Assignment.class, Grade.class, Enrollment.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public static final String DATABASE_NAME = "db-GradeTracker";

    public static final String USER_TABLE = "user";
    public static final String COURSE_TABLE = "course";
    public static final String GRADE_CATEGORY_TABLE = "gradeCategory";
    public static final String ASSIGNMENT_TABLE = "assignment";
    public static final String GRADE_TABLE = "grade";
    public static final String ENROLLMENT_TABLE = "enrollment";

    public abstract UserDao userDAO();

    public abstract CourseDao courseDao();

    public abstract GradeCategoryDao gradeCategoryDao();

    public abstract AssignmentDao assignmentDao();

    public abstract GradeDao gradeDao();

    public abstract EnrollmentDao enrollmentDao();
}
