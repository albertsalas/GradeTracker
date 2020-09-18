package com.example.gradetracker;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.gradetracker.Assignment;
import com.example.gradetracker.DB.AppDatabase;
import com.example.gradetracker.DB.AssignmentDao;

import java.util.Date;
import java.util.List;

public class EditAssignmentActivity extends AppCompatActivity {

    Assignment mAssignment = ShowAssignmentsActivity.mAssignment;
    EditText mDetail;
    EditText mDueDate;
    EditText mEarnedScore;
    EditText mMaxScore;
    EditText mAssignedDate;
    EditText mCategoryID;
    EditText mCourseID;

    List<Assignment>Assignment;
    AssignmentDao dao;

    Button submitButton;
    Button returnToAssignments;
    Button deleteButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_course);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mDetail = findViewById(R.id.mDetail);
        mDueDate = findViewById(R.id.due_date);
        mEarnedScore = findViewById(R.id.earned_score);
        mMaxScore = findViewById(R.id.max_score);
        mAssignedDate = findViewById(R.id.assigned_date);
        mCategoryID = findViewById(R.id.category_id);
        mCourseID = findViewById(R.id.course_id);

        returnToAssignments = findViewById(R.id.return_button);
        deleteButton = findViewById(R.id.delete_button);
        submitButton = findViewById(R.id.submit_button);

        mDetail.setHint(mAssignment.getDetails());
        mDueDate.setHint((CharSequence) mAssignment.getDueDate());
        mEarnedScore.setHint((int) mAssignment.getEarnedScore());
        mMaxScore.setHint((int) mAssignment.getMaxScore());
        mAssignedDate.setHint((CharSequence) mAssignment.getAssignedDate());
        mCategoryID.setHint((int) mAssignment.getCategoryID());
        mCourseID.setHint((int) mAssignment.getCourseId());

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                submitEdit();
            }
        });

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteAssignment();
            }
        });

        returnToAssignments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EditAssignmentActivity.this, ShowAssignmentsActivity.class);
                startActivity(intent);
            }
        });


    }
    boolean submitEdit(){

        String detail = mDetail.getText().toString();
        String dueDate = mDueDate.getText().toString();
        String assignedDate = mAssignedDate.getText().toString();
        int categoryID = Integer.parseInt(mCategoryID.getText().toString());
        int courseId = Integer.parseInt(mCourseID.getText().toString());

        long earnedScore = Long.parseLong(mEarnedScore.getText().toString());
        long maxScore = Long.parseLong(mMaxScore.getText().toString());

        Assignment = dao.getAllAssignments();

        if(!detail.isEmpty()){
            mAssignment.setDetails(detail);
        }
        if(!dueDate.toString().isEmpty()){
            mAssignment.setDueDate(dueDate);
        }
        if(!assignedDate.toString().isEmpty()){
            mAssignment.setAssignedDate(assignedDate);
        }
        if(!String.valueOf(categoryID).equals("")){
            mAssignment.setCategoryID(categoryID);
        }
        if(!String.valueOf(courseId).equals("")){
            mAssignment.setCourseId(courseId);
        }
        if(!String.valueOf(earnedScore).equals("")){
            mAssignment.setEarnedScore(earnedScore);
        }
        if(!String.valueOf(maxScore).equals("")){
            mAssignment.setMaxScore(maxScore);
        }
        dao.update(mAssignment);

        Toast.makeText(this, "Assignment was updated.", Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(EditAssignmentActivity.this, ShowAssignmentsActivity.class);
        startActivity(intent);

        return true;
    }

    void deleteAssignment(){

        Assignment = dao.getAllAssignments();
        dao.delete(mAssignment);
        Toast.makeText(this, "Assignment was deleted.", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(EditAssignmentActivity.this,ShowAssignmentsActivity.class);
        startActivity(intent);
    }
}
