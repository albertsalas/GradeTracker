package com.example.gradetracker.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import java.util.List;

import com.example.gradetracker.Assignment;
import com.example.gradetracker.DB.AppDatabase;
import com.example.gradetracker.DB.AssignmentDao;
import com.example.gradetracker.R;

/**
 * Activity for adding assignments and inserting them into the assignment table
 * @author Albert
 * @author Ben
 * @author Ozzie
 * @version 1.0
 */
public class AddAssignmentActivity extends AppCompatActivity {

    EditText mDetail;
    EditText mDueDate;
    EditText mEarnedScore;
    EditText mMaxScore;
    EditText mAssignedDate;

    Button mSubmitButton;
    Button mReturnButton;

    List<com.example.gradetracker.Assignment>Assignment;
    AssignmentDao dao;

    public int courseID;
    public int categoryID;

    static  Assignment mAssignment = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_assignment);

        courseID = getIntent().getExtras().getInt("courseID");
        categoryID = getIntent().getExtras().getInt("categoryID");

        mDetail = findViewById(R.id.mDetail);
        mDueDate = findViewById(R.id.due_date);
        mEarnedScore = findViewById(R.id.earned_score);
        mMaxScore = findViewById(R.id.max_score);
        mAssignedDate = findViewById(R.id.assigned_date);

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
                Intent intent = new Intent(AddAssignmentActivity.this, ShowAssignmentsActivity.class);
                startActivity(intent);
            }
        });

        dao = Room.databaseBuilder(this, AppDatabase.class, AppDatabase.ASSIGNMENT_TABLE)
                .allowMainThreadQueries()
                .build()
                .getAssignmentDao();
    }

    /**
     * Function for adding new assignment
     */
    public void addNewAssignment(){
        String detail = mDetail.getText().toString();
        String dueDate = mDueDate.getText().toString();
        String assignedDate = mAssignedDate.getText().toString();
        long earnedScore = Long.parseLong(mEarnedScore.getText().toString());
        long maxScore = Long.parseLong(mMaxScore.getText().toString());

        //Assignment = dao.getAllAssignments();
        Assignment newAssignment = new Assignment(detail,maxScore,earnedScore,assignedDate,dueDate,categoryID,courseID);
        dao.insert(newAssignment);

        Toast.makeText(this, "Assignment was added.", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(AddAssignmentActivity.this, ShowAssignmentsActivity.class);
        intent.putExtra("courseID", courseID);
        intent.putExtra("categoryID", categoryID);
        startActivity(intent);
    }

}
