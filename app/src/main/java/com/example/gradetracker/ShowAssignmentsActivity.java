//package com.example.gradetracker;
//import androidx.appcompat.app.AppCompatActivity;
//
//import android.app.Activity;
//import android.content.Intent;
//import android.os.Bundle;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.AdapterView;
//import android.widget.ArrayAdapter;
//import android.widget.Button;
//import android.widget.ListView;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import java.util.List;
//
//import com.example.gradetracker.Assignment;
//import com.example.gradetracker.Course;
//import com.example.gradetracker.DB.AppDatabase;
//import com.example.gradetracker.DB.AssignmentDao;
//import com.example.gradetracker.User;
//
//public class ShowAssignmentsActivity extends AppCompatActivity {
//
//    List<Assignment> assignments;
//    AssignmentDao dao;
//
//    Button clear_button;
//    Button returnToMainMenuButton;
//    Button addAssignmentButton;
//
//    //User mUser = MainActivity.mUser;
//    static Course mCourse = ShowCoursesActivity.mCourse;
//    static  Assignment mAssignment = null;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_show_assignments);
//
//        returnToMainMenuButton = findViewById(R.id.return_button);
//        clear_button = findViewById(R.id.clear_assignments_button);
//        addAssignmentButton = findViewById(R.id.add_assignment_button);
//
//        assignments = dao.getAllAssignment();
//
//        ListView assignmentsView = findViewById(R.id.assignment_list);
//
//        assignmentsView.setAdapter(new ShowAssignmentsActivity.AssignmentListAdapter( this,assignments) );
//
//        assignmentsView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                //make course object = the course at i which is the box clicked
//                mAssignment = assignments.get(position);
//                Intent intent = new Intent(getApplicationContext(),EditAssignmentActivity.class);
//                startActivity(intent);
//            }
//        });
//
//        addAssignmentButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(ShowAssignmentsActivity.this,AddAssignmentActivity.class);
//                startActivity(intent);
//            }
//        });
//
//        clear_button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(ShowAssignmentsActivity.this, "Assignments have been cleared.", Toast.LENGTH_SHORT).show();
//                Intent intent = new Intent(ShowAssignmentsActivity.this,LoginActivity.class);
//                startActivity(intent);
//            }
//        });
//
//        returnToMainMenuButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(ShowAssignmentsActivity.this,LoginActivity.class);
//                startActivity(intent);
//            }
//        });
//    }
//
//
//    public class AssignmentListAdapter extends ArrayAdapter<Assignment> {
//
//        public AssignmentListAdapter(Activity context, List<Assignment> assignments){
//            super(context, R.layout.row_layout ,assignments);
//        }
//
//        @Override
//        public View getView(int position, View view, ViewGroup parent) {
//
//            LayoutInflater inflater= ShowAssignmentsActivity.this.getLayoutInflater();
//            View rowView=inflater.inflate(R.layout.row_layout, null,true);
//            TextView rowField = rowView.findViewById(R.id.row_id);
//
//            //set the value of a row in the ListView to the flight info using toString()
//            rowField.setText(assignments.get(position).toString());
//            return rowView;
//        }
//    }
//}
//
