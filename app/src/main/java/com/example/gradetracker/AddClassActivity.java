package com.example.gradetracker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.gradetracker.Activities.ClassScheduleActivity;
import com.example.gradetracker.DB.AppDatabase;
import com.example.gradetracker.DB.CourseDao;
import com.example.gradetracker.DB.EnrollmentDao;

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
    int extraID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_class);

        ex = getIntent().getExtras();
        extraID = ex.getInt("uID");

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

        //getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close);

        setTitle("Add Course");

        saveCourse();

    }

    private void saveCourse(){

        add = findViewById(R.id.addCourseButton);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddClassActivity.this, ClassScheduleActivity.class);
                intent.putExtra("uID", extraID);

                String t = title.getText().toString();
                String i = instructor.getText().toString();
                String d = description.getText().toString();
                String sd = startDate.getText().toString();
                String ed = endDate.getText().toString();

                Log.i("DATE", sd);

                Course newCourse = new Course(i, t, d, sd, ed);
                courseDao.insert(newCourse);

                Course tempCourse = courseDao.getLastCourse();

                enrollmentDao.insert(new Enrollment(extraID, tempCourse.getCourseID()));

                startActivity(intent);
            }
        });

    }

}