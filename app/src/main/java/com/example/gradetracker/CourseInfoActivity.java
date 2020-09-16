package com.example.gradetracker;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class CourseInfoActivity extends AppCompatActivity {

    TextView course;
    TextView instructor;
    TextView description;
    TextView startDate;
    TextView endDate;
    Button viewAssignments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_info);
    }
}