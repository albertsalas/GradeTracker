package com.example.gradetracker.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.gradetracker.ClassAdapter;
import com.example.gradetracker.Course;
import com.example.gradetracker.DB.AppDatabase;
import com.example.gradetracker.DB.CourseDao;
import com.example.gradetracker.DB.EnrollmentDao;
import com.example.gradetracker.Enrollment;
import com.example.gradetracker.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

/**
 * ClassScheduleActivity is for showing the user's current courses
 * @author
 * @version
 */
public class ClassScheduleActivity extends AppCompatActivity implements ClassAdapter.OnCourseListener {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    public int userID;
    CourseDao courseDao;
    List<Enrollment> mEnrollments;
    EnrollmentDao enrollmentDao;
    List<Course> tempCourses = new ArrayList<>();

    /**
     * onCreate is for displaying the information shown when the activity is made
     * @param savedInstanceState is the current savedInstanceState
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class_schedule);

        //get extra from previous activity
        if(getIntent().hasExtra("userID")){
            userID = getIntent().getExtras().getInt("userID");
        }
        setTitle("Course Schedule");

        //add courses button
        FloatingActionButton buttonAddCourse = findViewById(R.id.floatingActionButton);
        buttonAddCourse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ClassScheduleActivity.this, AddClassActivity.class);
                intent.putExtra("userID", userID);
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


        mEnrollments = enrollmentDao.getStudentsEnrolledClasses(userID);
        // the reason I was getting a null was because I wasn't initializing the list
        // duh that's on me for not googling sooner
        //TODO: write test for getting specific courses
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
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleCallback);
        itemTouchHelper.attachToRecyclerView(recyclerView);
    }

    /**
     *
     * @param menu is the top bar for displaying the settings button on this activity
     * @return true
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.add_course_menu, menu); // was meant for adding course but re-purposed it for this activity
        return true;
    }

    /**
     * for clicking the menu options
     * @param item is the item clicked in the menu bar
     * @return the item clicked
     */
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.settings: //user profile settings
                Intent intent = new Intent(this, ProfileActivity.class);
                intent.putExtra("userID", userID);
                startActivity(intent);
                break;
            case R.id.logoutIcon:
                Intent logoutIntent = new Intent(this, LoginActivity.class);
                startActivity(logoutIntent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * Method for allowing the recycler view to grab each course individually and display their information
     * @param position is the position within the recycler view that corresponds to that course
     */
    @Override
    public void onCourseClick(int position) {
        Intent intent = new Intent(this, CourseInfoActivity.class);
        intent.putExtra("courseID", tempCourses.get(position));
        intent.putExtra("userID", userID);
        startActivity(intent);
    }


    Course deletedCourse = null;
    Enrollment deletedEnrollment = null;
    /**
     * Method for swiping on items within the recycler view and deleting the course
     * @param ItemTouchHelper is the direction that we want to swipe
     * @param dragDirs is set to 0 because we aren't moving the items around
     */
    ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
        /**
         * Method that's never used because we aren't moving the items around
         * @param recyclerView is the recyclerView we instantiated in onCreate
         * @param viewHolder is the current viewHolder
         * @param target is the item associated with the view
         * @return
         */
        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
            return false;
        }

        /**
         * Method for getting the swipe direction and interacting with it.
         * @param viewHolder is the current viewHolder
         * @param direction is the direction we swipe in
         */
        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
            int position = viewHolder.getAdapterPosition();

            switch(direction){
                case ItemTouchHelper.LEFT:
                    deletedCourse = tempCourses.get(position);
                    deletedEnrollment = enrollmentDao.getEnrolledClass(deletedCourse.getCourseID());
                    courseDao.delete(deletedCourse);
                    enrollmentDao.delete(deletedEnrollment);
                    tempCourses.remove(position);
                    adapter.notifyItemRemoved(position);
                    Toast.makeText(ClassScheduleActivity.this, "Course Deleted", Toast.LENGTH_SHORT).show();
                    break;
//                case ItemTouchHelper.RIGHT:
//                    break;
            }
        }
    };


}