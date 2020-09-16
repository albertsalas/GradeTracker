package com.example.gradetracker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.gradetracker.DB.AppDatabase;
import com.example.gradetracker.DB.CourseDao;

public class CourseInfoActivity extends AppCompatActivity {

    TextView course;
    TextView instructor;
    TextView description;
    TextView startDate;
    TextView endDate;
    Button viewAssignments;
    Course currentCourse;
    CourseDao courseDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_info);
        setTitle("Course Information");
        if(getIntent().hasExtra("courseID")){
            currentCourse = getIntent().getParcelableExtra("courseID");
        }

//        courseDao = Room.databaseBuilder(this, AppDatabase.class, AppDatabase.COURSE_TABLE)
//                .allowMainThreadQueries()
//                .build()
//                .getCourseDao();

        course = findViewById(R.id.courseInfoTitle);
        instructor = findViewById(R.id.courseInfoInstructor);
        description = findViewById(R.id.courseInfoDescription);
        startDate = findViewById(R.id.courseInfoStartDate);
        endDate = findViewById(R.id.courseInfoEndDate);

        course.setText(currentCourse.getTitle());
        instructor.setText(currentCourse.getInstructor());
        description.setText(currentCourse.getDescription());
        startDate.setText(currentCourse.getStartDate());
        endDate.setText(currentCourse.getEndDate());

        viewAssignments = findViewById(R.id.courseInfoViewAssignmentsButton);
        viewAssignments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CourseInfoActivity.this, AssignmentActivity.class);
                startActivity(intent);
            }
        });


    }

    //menu bar for displaying the settings button on this activity
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.add_course_menu, menu); // was meant for adding course but re-purposed it for this activity
        return true;
    }
    //for clicking the menu options
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.settings: //course settings
                Intent intent = new Intent(this, UpdateCourseInfoActivity.class);
                intent.putExtra("courseID", currentCourse.getCourseID());
                startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}