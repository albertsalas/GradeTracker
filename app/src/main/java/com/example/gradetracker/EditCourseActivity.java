//package com.example.gradetracker;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.view.View;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.Toast;
//
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.appcompat.widget.Toolbar;
//
//import com.example.gradetracker.Course;
//import com.example.gradetracker.DB.AppDatabase;
//import com.example.gradetracker.DB.CourseDao;
//
//import java.util.Date;
//import java.util.List;
//
//public class EditCourseActivity extends AppCompatActivity {
//
//    Course mCourse = ShowCoursesActivity.mCourse;
//    EditText mInstructorName;
//    EditText mCourseTitle;
//    EditText mCourseDescription;
//    EditText mStartDate;
//    EditText mEndDate;
//
//    List<Course>courses;
//    CourseDao dao;
//
//    Button submitButton;
//    Button returnToCourses;
//    Button deleteButton;
//    Button viewAssignmentsButton;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_edit_course);
//        Toolbar toolbar = findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//
//        mInstructorName = findViewById(R.id.instructor_name);
//        mCourseTitle = findViewById(R.id.course_title);
//        mCourseDescription = findViewById(R.id.description);
//        mStartDate = findViewById(R.id.start_date);
//        mEndDate = findViewById(R.id.end_date);
//
//        returnToCourses = findViewById(R.id.return_button);
//        deleteButton = findViewById(R.id.delete_button);
//        submitButton = findViewById(R.id.submit_button);
//        viewAssignmentsButton = findViewById(R.id.view_assignment_button);
//
//        mInstructorName.setHint(mCourse.getInstructor());
//        mCourseTitle.setHint(mCourse.getTitle());
//        mCourseDescription.setHint(mCourse.getDescription());
//        mStartDate.setHint((CharSequence) mCourse.getStartDate());
//        mEndDate.setHint((CharSequence) mCourse.getEndDate());
//
//        submitButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                submitEdit();
//            }
//        });
//
//        viewAssignmentsButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(EditCourseActivity.this,ShowAssignmentsActivity.class);
//                startActivity(intent);
//            }
//        });
//
//        deleteButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                deleteCourse();
//            }
//        });
//
//        returnToCourses.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(EditCourseActivity.this,ShowCoursesActivity.class);
//                startActivity(intent);
//            }
//        });
//
//
//    }
//    boolean submitEdit(){
//
//        String instructorName = mInstructorName.getText().toString();
//        String courseTitle = mCourseTitle.getText().toString();
//        String courseDescription = mCourseDescription.getText().toString();
//        String startDate = mStartDate.getText().toString();
//        String endDate = mEndDate.getText().toString();
//
//        courses = dao.getAllCourses();
//
//        if(!instructorName.isEmpty()){
//            mCourse.setInstructor(instructorName);
//        }
//        if(!courseTitle.isEmpty()){
//            mCourse.setTitle(courseTitle);
//        }
//        if(!courseDescription.isEmpty()){
//            mCourse.setDescription(courseDescription);
//        }
//        if(!startDate.isEmpty()){
//            mCourse.setStartDate(startDate);
//        }
//        if(!endDate.isEmpty()){
//            mCourse.setEndDate(endDate);
//        }
//
//        dao.update(mCourse);
//
//        Toast.makeText(this, "Course was updated.", Toast.LENGTH_SHORT).show();
//
//        Intent intent = new Intent(EditCourseActivity.this,LoginActivity.class);
//        startActivity(intent);
//
//        return true;
//    }
//
//    void deleteCourse(){
//
//        courses = dao.getAllCourses();
//        dao.delete(mCourse);
//        Toast.makeText(this, "Course was deleted.", Toast.LENGTH_SHORT).show();
//        Intent intent = new Intent(EditCourseActivity.this,LoginActivity.class);
//        startActivity(intent);
//
//    }
//}
