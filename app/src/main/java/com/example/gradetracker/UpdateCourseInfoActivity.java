package com.example.gradetracker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.gradetracker.DB.AppDatabase;
import com.example.gradetracker.DB.CourseDao;

/**
 * UpdateCourseInfoActivity is for updating the current course information
 */
public class UpdateCourseInfoActivity extends AppCompatActivity {
    EditText course;
    EditText instructor;
    EditText description;
    EditText startDate;
    EditText endDate;
    Button update;
    int courseID;
    Course tempCourse;
    CourseDao courseDao;

    String newCourse;
    String newInstructor;
    String newDescription;
    String newStartDate;
    String newEndDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_course_info);

        if(getIntent().hasExtra("courseID")){
            courseID = getIntent().getExtras().getInt("courseID");
        }

        courseDao = Room.databaseBuilder(this, AppDatabase.class, AppDatabase.COURSE_TABLE)
                .allowMainThreadQueries()
                .build()
                .getCourseDao();

        tempCourse = courseDao.getCourse(courseID);

        course = findViewById(R.id.updateCourseTitle);
        instructor = findViewById(R.id.updateCourseInstructor);
        description = findViewById(R.id.updateCourseDescription);
        startDate = findViewById(R.id.updateCourseStartDate);
        endDate = findViewById(R.id.updateCourseEndDate);

        course.setText(tempCourse.getTitle());
        instructor.setText(tempCourse.getInstructor());
        description.setText(tempCourse.getDescription());
        startDate.setText(tempCourse.getStartDate());
        endDate.setText(tempCourse.getEndDate());

    }

    public void updateCourse(){
        update = findViewById(R.id.updateCourseButton);
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                newCourse = course.getText().toString();
                newInstructor = instructor.getText().toString();
                newDescription = description.getText().toString();
                newStartDate = startDate.getText().toString();
                newEndDate = endDate.getText().toString();


            }
        });
    }
}