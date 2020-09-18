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

import com.example.gradetracker.AssignmentActivity;
import com.example.gradetracker.Course;
import com.example.gradetracker.DB.CourseDao;
import com.example.gradetracker.R;
import com.example.gradetracker.ShowAssignmentsActivity;
import com.example.gradetracker.UpdateCourseInfoActivity;

/**
 * Activity for displaying course information
 * @author
 * @version
 */
public class CourseInfoActivity extends AppCompatActivity {

    TextView course;
    TextView instructor;
    TextView description;
    TextView startDate;
    TextView endDate;
    Button viewAssignments;
    Course currentCourse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_info);
        setTitle("Course Information");
        if(getIntent().hasExtra("courseID")){
            currentCourse = getIntent().getParcelableExtra("courseID");
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

        viewAssignments = findViewById(R.id.courseInfoViewAssignmentsButton);
        viewAssignments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CourseInfoActivity.this, ShowAssignmentsActivity.class);
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
                startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}