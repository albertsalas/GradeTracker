package com.example.gradetracker.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.gradetracker.Course;
import com.example.gradetracker.DB.AppDatabase;
import com.example.gradetracker.DB.CourseDao;
import com.example.gradetracker.DB.EnrollmentDao;
import com.example.gradetracker.Enrollment;
import com.example.gradetracker.R;

/**
 * Activity for adding classes for the user
 * @author Albert
 * @author Ben
 * @author Ozzie
 * @version 1.0
 */
public class AddClassActivity extends AppCompatActivity {

    EditText instructor;
    EditText title;
    EditText description;
    EditText startDate;
    EditText endDate;
    Button add;

    EnrollmentDao enrollmentDao;
    CourseDao courseDao;
    Enrollment enrollment;
    Bundle ex;
    int userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_class);

        ex = getIntent().getExtras();
        userID = ex.getInt("userID");

        instructor = findViewById(R.id.addCourseInstructor);
        title = findViewById(R.id.addCourseTitle);
        description = findViewById(R.id.addCourseDescription);
        startDate = findViewById(R.id.addCourseStartDate);
        endDate = findViewById(R.id.addCourseEndDate);


        enrollmentDao = Room.databaseBuilder(this, AppDatabase.class, AppDatabase.ENROLLMENT_TABLE)
                .allowMainThreadQueries()
                .build()
                .getEnrollmentDao();
        courseDao = Room.databaseBuilder(this, AppDatabase.class, AppDatabase.COURSE_TABLE)
                .allowMainThreadQueries()
                .build()
                .getCourseDao();

        setTitle("Add Course");

        saveCourse();

    }

    /**
     * Function for saving the course into the course database
     */
    private void saveCourse(){

        add = findViewById(R.id.addCourseButton);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddClassActivity.this, ClassScheduleActivity.class);
                intent.putExtra("userID", userID);

                String t = title.getText().toString();
                String i = instructor.getText().toString();
                String d = description.getText().toString();
                String sd = startDate.getText().toString();
                String ed = endDate.getText().toString();

                Course newCourse = new Course(i, t, d, sd, ed);
                courseDao.insert(newCourse);

                Course tempCourse = courseDao.getLastCourse();

                enrollmentDao.insert(new Enrollment(userID, tempCourse.getCourseID()));

                startActivity(intent);
            }
        });

    }

}