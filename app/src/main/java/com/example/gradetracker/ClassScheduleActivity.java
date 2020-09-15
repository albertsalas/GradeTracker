package com.example.gradetracker;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
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

        /**store extras for any activities*/
        if(getIntent().hasExtra("uID")){
            extraID = getIntent().getExtras().getInt("uID");
        }
        setTitle("Course Schedule");

        //add courses button
        FloatingActionButton buttonAddCourse = findViewById(R.id.floatingActionButton);
        buttonAddCourse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ClassScheduleActivity.this, AddClassActivity.class);
                intent.putExtra("uID", extraID);
                startActivity(intent);
            }
        });

        //for helping to retrieve students individual courses
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
        for(Enrollment enrollment: mEnrollments){
            tempCourses.add(courseDao.getCourse(enrollment.getCourseID()));
        }

        //recycler view
        recyclerView = findViewById(R.id.recycle_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        adapter = new ClassAdapter(tempCourses, this);
        recyclerView.setAdapter(adapter);

        //for the swipe feature
//        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleCallback);
//        itemTouchHelper.attachToRecyclerView(recyclerView);
    }
    //menu bar for displaying the settings button on this activity
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.add_course_menu, menu); // was meant for adding course but re-purposed it for this activity
        return true;
    }
    //for clicking the menu options
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.settings: //user profile settings
                Intent intent = new Intent(this, ProfileActivity.class);
                intent.putExtra("userID", extraID);
                startActivity(intent);

        }
        return super.onOptionsItemSelected(item);
    }
    //this is for sending the users to their current courses assignments, since we're using a recycler view the only way to do
    //this is by making courses parcelable
    @Override
    public void onCourseClick(int position) {
        //mCourses.get(position);
        Intent intent = new Intent(this, AssignmentActivity.class);
        intent.putExtra("courseID", tempCourses.get(position));
        startActivity(intent);
    }

    /**for swiping but not sure if you guys want it implemented*/
//    Course deletedCourse = null;
//
//    ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT | ItemTouchHelper.LEFT) {
//        @Override
//        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
//            return false;
//        }
//
//        @Override
//        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
//            int position = viewHolder.getAdapterPosition();
//            switch(direction){
//                case ItemTouchHelper.LEFT:
//                    deletedCourse = tempCourses.get(position);
//                    tempCourses.remove(position);
//                    adapter.notifyItemRemoved(position);
//                    break;
//                case ItemTouchHelper.RIGHT:
//                    break;
//            }
//        }
//    };


}