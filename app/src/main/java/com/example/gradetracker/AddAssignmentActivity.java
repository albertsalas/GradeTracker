package com.example.gradetracker;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Date;
import java.util.List;

import com.example.gradetracker.Assignment;
import com.example.gradetracker.Course;
import com.example.gradetracker.DB.AppDatabase;
import com.example.gradetracker.DB.AssignmentDao;
import com.example.gradetracker.DB.CourseDao;
import com.example.gradetracker.User;


public class AddAssignmentActivity extends AppCompatActivity {

    EditText mDetail;
    EditText mDueDate;
    EditText mEarnedScore;
    EditText mMaxScore;
    EditText mAssignedDate;
    EditText mCategoryID;
    EditText mCourseID;


    Button mSubmitButton;
    Button mReturnButton;

    List<Assignment>Assignment;
    AssignmentDao dao;

    static Course mCourse = ShowCoursesActivity.mCourse;
    static  Assignment mAssignment = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_assignment);

        mDetail = findViewById(R.id.mDetail);
        mDueDate = findViewById(R.id.due_date);
        mEarnedScore = findViewById(R.id.earned_score);
        mMaxScore = findViewById(R.id.max_score);
        mAssignedDate = findViewById(R.id.assigned_date);
        mCategoryID = findViewById(R.id.category_id);
        mCourseID = findViewById(R.id.course_id);

        mReturnButton = findViewById(R.id.return_button);
        mSubmitButton = findViewById(R.id.submit_button);

        mSubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addNewAssignment();
            }
        });

        mReturnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddAssignmentActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }

    boolean addNewAssignment(){
        String detail = mDetail.getText().toString();
        String dueDate = mDueDate.getText().toString();
        String assignedDate = mAssignedDate.getText().toString();
        int categoryID = Integer.parseInt(mCategoryID.getText().toString());
        int courseId = Integer.parseInt(mCourseID.getText().toString());

        long earnedScore = Long.parseLong(mEarnedScore.getText().toString());
        long maxScore = Long.parseLong(mMaxScore.getText().toString());

        Assignment = dao.getAllAssignment();
        Assignment newAssignment = new Assignment(detail,earnedScore,maxScore,assignedDate,dueDate,categoryID,courseId);
        dao.insert(newAssignment);

        Toast.makeText(this, "Assignment was added.", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(AddAssignmentActivity.this, LoginActivity.class);
        startActivity(intent);

        return true;
    }

}
