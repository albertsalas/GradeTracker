package com.example.gradetracker;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.example.gradetracker.DB.AppDatabase;
import com.example.gradetracker.DB.CourseDao;
import com.example.gradetracker.DB.EnrollmentDao;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class ClassScheduleActivity extends AppCompatActivity implements ClassAdapter.OnCourseListener{
    //private CourseViewModel courseViewModel;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    Bundle ex;
    public int extraID;
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
        setTitle("Course Schedule");

        FloatingActionButton buttonAddCourse = findViewById(R.id.floatingActionButton);
        buttonAddCourse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ClassScheduleActivity.this, AddClassActivity.class);
                intent.putExtra("uID", extraID);
                startActivity(intent);
            }
        });

        enrollmentDao = Room.databaseBuilder(this, AppDatabase.class, AppDatabase.ENROLLMENT_TABLE)
                .allowMainThreadQueries()
                .build()
                .getEnrollmentDao();

        courseDao = Room.databaseBuilder(this, AppDatabase.class, AppDatabase.COURSE_TABLE)
                .allowMainThreadQueries()
                .build()
                .getCourseDao();


        mEnrollments = enrollmentDao.getStudentsEnrolledClasses(extraID);
        // the reason I was getting a null was because I wasn't initializing the list
        // duh that's on me for not googling sooner
        //TODO: write test for getting specific courses
        tempCourses = new ArrayList<>();
        mCourses = courseDao.getAllCourses();
        for(Enrollment enrollment: mEnrollments){
            tempCourses.add(courseDao.getCourse(enrollment.getCourseID()));
        }

        //recycler view
        recyclerView = findViewById(R.id.recycle_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        adapter = new ClassAdapter(tempCourses, this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.add_course_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.settings:
                Intent intent = new Intent(this, ProfileActivity.class);
                startActivity(intent);

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCourseClick(int position) {
        //mCourses.get(position);
        Log.i("it got", "CLICKED");
        //Intent intent = new Intent(this,)
    }


}