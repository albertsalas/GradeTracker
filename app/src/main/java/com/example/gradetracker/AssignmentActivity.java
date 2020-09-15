package com.example.gradetracker;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

public class AssignmentActivity extends AppCompatActivity {
    private static final String TAG = "AssignmentActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assignment);

        if(getIntent().hasExtra("courseID")){
            Course course = getIntent().getParcelableExtra("courseID");
            Log.d(TAG, "onCreate:" + course.getTitle());
        }

    }
}