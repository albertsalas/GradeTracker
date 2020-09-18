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
import com.example.gradetracker.R;

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
    public int courseID;
    public int userID;

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
            userID = getIntent().getExtras().getInt("userID");
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

        updateCourse();
    }

    /**
     * updateCourse for updating the course when the button is clicked
     */
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

                tempCourse.setTitle(newCourse);
                tempCourse.setInstructor(newInstructor);
                tempCourse.setDescription(newDescription);
                tempCourse.setStartDate(newStartDate);
                tempCourse.setEndDate(newEndDate);

                courseDao.update(tempCourse);
                Intent intent = new Intent(UpdateCourseInfoActivity.this, ClassScheduleActivity.class);
                intent.putExtra("userID", userID);
                startActivity(intent);


            }
        });
    }
}