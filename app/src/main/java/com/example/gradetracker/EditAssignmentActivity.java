package com.example.gradetracker;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.room.Room;

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


    List<Assignment>Assignment;
    AssignmentDao dao;
    Button submitButton;
    Button returnToAssignments;
    Button deleteButton;
    int assignmentID;
    int userID;
    int courseID;
    GradeCategory gradeCategory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_assignment);

        assignmentID = getIntent().getExtras().getInt("assignmentID");
        if(getIntent().hasExtra("categoryID")){
            userID = getIntent().getExtras().getInt("userID");
            courseID = getIntent().getExtras().getInt("courseID");
            gradeCategory = getIntent().getParcelableExtra("categoryID");
        }

        dao = Room.databaseBuilder(this, AppDatabase.class, AppDatabase.ASSIGNMENT_TABLE)
                .allowMainThreadQueries()
                .build()
                .getAssignmentDao();

        mDetail = findViewById(R.id.details);
        mDueDate = findViewById(R.id.due_date);
        mEarnedScore = findViewById(R.id.earned_score);
        mMaxScore = findViewById(R.id.max_score);
        mAssignedDate = findViewById(R.id.assigned_date);


        returnToAssignments = findViewById(R.id.return_button);
        deleteButton = findViewById(R.id.delete_button);
        submitButton = findViewById(R.id.submit_button);

        mDetail.setText(mAssignment.getDetails());
        mDueDate.setText(mAssignment.getDueDate());
        mEarnedScore.setText(String.valueOf(mAssignment.getEarnedScore()) );
        mMaxScore.setText(String.valueOf(mAssignment.getMaxScore()));
        mAssignedDate.setText((CharSequence) mAssignment.getAssignedDate());

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


        double earnedScore = Double.parseDouble(mEarnedScore.getText().toString());
        double maxScore = Double.parseDouble(mMaxScore.getText().toString());

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
        if(!String.valueOf(earnedScore).equals("")){
            mAssignment.setEarnedScore(earnedScore);
        }
        if(!String.valueOf(maxScore).equals("")){
            mAssignment.setMaxScore(maxScore);
        }
        dao.update(mAssignment);

        Toast.makeText(this, "Assignment was updated.", Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(EditAssignmentActivity.this, ShowAssignmentsActivity.class);
        intent.putExtra("userID", userID);
        intent.putExtra("courseID", courseID);
        intent.putExtra("categoryID", gradeCategory);
        startActivity(intent);

        return true;
    }

    void deleteAssignment(){

        Assignment = dao.getAllAssignments();
        dao.delete(mAssignment);
        Toast.makeText(this, "Assignment was deleted.", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(EditAssignmentActivity.this,ShowAssignmentsActivity.class);
        intent.putExtra("userID", userID);
        intent.putExtra("courseID", courseID);
        intent.putExtra("categoryID", gradeCategory);
        startActivity(intent);
    }
}
