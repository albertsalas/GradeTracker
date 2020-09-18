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
import com.example.gradetracker.CategoriesAdapter;
import com.example.gradetracker.GradeCategory;
import com.example.gradetracker.R;
import com.example.gradetracker.ShowAssignmentsActivity;


import java.util.ArrayList;
import java.util.List;

public class CategoriesActivity extends AppCompatActivity implements CategoriesAdapter.OnCategoryListener {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    public int userID;
    public int courseID;
    List<GradeCategory> tempCategories = new ArrayList<>();

    /**
     * onCreate is for displaying the information shown when the activity is made
     * @param savedInstanceState is the current savedInstanceState
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);

        //get extra from previous activity
        if(getIntent().hasExtra("userID")){
            userID = getIntent().getExtras().getInt("userID");
            courseID = getIntent().getExtras().getInt("courseID");
        }
        setTitle("Course Grades");
        GradeCategory one = new GradeCategory("Exams", 40.0, 1, "9/17/2020");
        GradeCategory two = new GradeCategory("Quizzes", 30.0, 2, "9/17/2020");
        GradeCategory three = new GradeCategory("Homework", 30.0, 3, "9/17/2020");
        tempCategories.add(one);
        tempCategories.add(two);
        tempCategories.add(three);

        //recycler view
        recyclerView = findViewById(R.id.recycle_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        adapter = new CategoriesAdapter(tempCategories, this);
        recyclerView.setAdapter(adapter);
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
    public void onCategoryClick(int position) {
        Intent intent = new Intent(this, ShowAssignmentsActivity.class);
        intent.putExtra("categoryID", tempCategories.get(position));
        intent.putExtra("userID", userID);
        intent.putExtra("courseID", courseID);
        startActivity(intent);
    }

}