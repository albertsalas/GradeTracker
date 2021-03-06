package com.example.gradetracker.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.gradetracker.Assignment;
import com.example.gradetracker.CategoriesAdapter;
import com.example.gradetracker.Course;
import com.example.gradetracker.DB.AppDatabase;
import com.example.gradetracker.DB.AssignmentDao;
import com.example.gradetracker.DB.CourseDao;
import com.example.gradetracker.DB.GradeCategoryDao;
import com.example.gradetracker.DB.GradeDao;
import com.example.gradetracker.Grade;
import com.example.gradetracker.GradeCategory;
import com.example.gradetracker.R;


import java.util.ArrayList;
import java.util.List;

/**
 * Activity for showing the categories
 * @author Albert
 * @author Ben
 * @author Ozzie
 * @version 1.0
 */
public class CategoriesActivity extends AppCompatActivity implements CategoriesAdapter.OnCategoryListener {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    public int userID;
    public int courseID;
    GradeCategoryDao gradeCategoryDao;
    List<GradeCategory> tempCategories = new ArrayList<>();
    AssignmentDao assignmentDao;
    List<Assignment> assignments;
    GradeDao gradeDao;
    List<Grade> grades;
    List<GradeCategory> gradeWeights = new ArrayList<>();
    CourseDao courseDao;
    Course course;


    /**
     * onCreate is for displaying the information shown when the activity is made
     * @param savedInstanceState is the current savedInstanceState
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);
        grades = new ArrayList<>();
        assignments = new ArrayList<>();

        gradeCategoryDao = Room.databaseBuilder(this, AppDatabase.class, AppDatabase.GRADE_CATEGORY_TABLE)
                .allowMainThreadQueries()
                .build()
                .getGradeCategoryDao();
        gradeDao = Room.databaseBuilder(this,AppDatabase.class, AppDatabase.GRADE_TABLE)
                .allowMainThreadQueries()
                .build()
                .getGradeDao();
        assignmentDao = Room.databaseBuilder(this,AppDatabase.class, AppDatabase.ASSIGNMENT_TABLE)
                .allowMainThreadQueries()
                .build()
                .getAssignmentDao();
        courseDao = Room.databaseBuilder(this,AppDatabase.class, AppDatabase.COURSE_TABLE)
                .allowMainThreadQueries()
                .build()
                .getCourseDao();
        //get extra from previous activity
        if(getIntent().hasExtra("userID")){
            userID = getIntent().getExtras().getInt("userID");
            courseID = getIntent().getExtras().getInt("courseID");
        }
        setTitle("Course Grades");

        tempCategories = gradeCategoryDao.getAllCategories();

        //recycler view
        recyclerView = findViewById(R.id.recycle_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        adapter = new CategoriesAdapter(tempCategories, this);
        recyclerView.setAdapter(adapter);
        weightedGrades();
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
                Intent logoutIntent = new Intent(this, MainActivity.class);
                startActivity(logoutIntent);
                break;
            case  R.id.home:
                Intent home = new Intent(this, ClassScheduleActivity.class);
                home.putExtra("userID", userID);
                startActivity(home);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * Method for allowing the recycler view to grab each course individually and display their information
     * @param position is the position within the recycler view that corresponds to that course
     */
    @Override
    public void onCategoryClick(int position) {
        Intent intent = new Intent(this, ShowAssignmentsActivity.class);
        intent.putExtra("categoryID", tempCategories.get(position).getCategoryID());
        intent.putExtra("userID", userID);
        intent.putExtra("courseID", courseID);
        startActivity(intent);
    }

    public void weightedGrades(){
        grades = gradeDao.getAllGradesbyCourseID(courseID);
        gradeWeights = gradeCategoryDao.getAllCategories();
        double newsum = 0;
        for(GradeCategory g: gradeWeights){
            assignments = assignmentDao.getAllAssignmentsByCategory(g.getCategoryID());
            double sum = 0;
            double totalPoints = 0;
            for(Assignment a: assignments){
                if(a.getCourseId() == courseID){
                    sum += a.getEarnedScore();
                    totalPoints += a.getMaxScore();
                }
            }
            if(totalPoints != 0){
                newsum += ((sum / totalPoints) * (g.getWeight() / 100));
            }
        }
        course = courseDao.getCourse(courseID);
        course.setCourseGrade(newsum * 100);
        courseDao.update(course);

    }

}