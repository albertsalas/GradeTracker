package com.example.gradetracker;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.gradetracker.DB.AppDatabase;
import com.example.gradetracker.DB.CourseDao;
import com.example.gradetracker.DB.EnrollmentDao;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class ClassScheduleActivity extends AppCompatActivity {
    //private CourseViewModel courseViewModel;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    Bundle ex;
    int extraID;
    CourseDao courseDao;
    List<Course> mCourses;
    List<Enrollment> mEnrollments;
    EnrollmentDao enrollmentDao;
    List<Course> tempCourses;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class_schedule);

        ex = getIntent().getExtras();
        extraID = ex.getInt("uID");

        FloatingActionButton buttonAddCourse = findViewById(R.id.floatingActionButton);
        buttonAddCourse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ClassScheduleActivity.this, AddClassActivity.class);
                intent.putExtra("uID", extraID);
                startActivity(intent);
            }
        });

        enrollmentDao = Room.databaseBuilder(this,AppDatabase.class, AppDatabase.ENROLLMENT_TABLE)
                .allowMainThreadQueries()
                .build()
                .getEnrollmentDao();

        courseDao = Room.databaseBuilder(this, AppDatabase.class, AppDatabase.COURSE_TABLE)
                .allowMainThreadQueries()
                .build()
                .getCourseDao();
        mEnrollments = enrollmentDao.getAllEnrollments();
//        for(Enrollment en : mEnrollments){
//            System.out.println(en.getCourseID());
//            String temp = Integer.toString(en.getCourseID());
//            Log.i("tempID ", temp);
//        }


        mCourses = courseDao.getAllCourses();

        recyclerView = findViewById(R.id.recycle_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        adapter = new ClassAdapter(mCourses);
        recyclerView.setAdapter(adapter);


//        @Override
//        public void onChanged(@Nullable List<Course> courses){
//            adapter.setCourses(courses);
//        }

    }
}