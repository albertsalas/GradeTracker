package com.example.gradetracker.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.gradetracker.Course;
import com.example.gradetracker.R;

/**
 * Activity for displaying course information
 * @author Albert
 * @author Ben
 * @author Ozzie
 * @version 1.0
 */
public class CourseInfoActivity extends AppCompatActivity {

    TextView course;
    TextView instructor;
    TextView description;
    TextView startDate;
    TextView endDate;
    Button viewGrades;
    Course currentCourse;
    public int userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_info);
        setTitle("Course Information");
        if(getIntent().hasExtra("courseID")){
            currentCourse = getIntent().getParcelableExtra("courseID");
            userID = getIntent().getExtras().getInt("userID");
        }

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

        viewGrades = findViewById(R.id.courseInfoViewAssignmentsButton);
        viewGrades.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CourseInfoActivity.this, CategoriesActivity.class);
                intent.putExtra("courseID", currentCourse.getCourseID());
                intent.putExtra("userID", userID);
                startActivity(intent);
            }
        });


    }

    /**
     * Menu for displaying the settings button that's re-purposed for this activity
     * @param menu is the menu for course updating
     * @return true
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.add_course_menu, menu);
        return true;
    }

    /**
     * Function for clicking the items in the menu bar
     * @param item is the current item clicked
     * @return the item clicked
     */
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.settings: //course settings
                Intent intent = new Intent(this, UpdateCourseInfoActivity.class);
                intent.putExtra("courseID", currentCourse.getCourseID());
                intent.putExtra("userID", userID);
                startActivity(intent);
                break;
            case R.id.logoutIcon:
                Intent logoutIntent = new Intent(this, LoginActivity.class);
                startActivity(logoutIntent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}