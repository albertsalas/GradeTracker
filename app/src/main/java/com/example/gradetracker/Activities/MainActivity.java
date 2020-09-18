package com.example.gradetracker.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.gradetracker.Course;
import com.example.gradetracker.DB.AppDatabase;
import com.example.gradetracker.DB.CourseDao;
import com.example.gradetracker.DB.EnrollmentDao;
import com.example.gradetracker.DB.GradeCategoryDao;
import com.example.gradetracker.DB.UserDao;
import com.example.gradetracker.Enrollment;
import com.example.gradetracker.GradeCategory;
import com.example.gradetracker.R;
import com.example.gradetracker.User;

import java.util.List;

/**
 * Activity for displaying the login and sign-up button
 * @author Ozzie
 * @version 1.0
 */
public class MainActivity extends AppCompatActivity {

    Button login;
    Button signup;
    UserDao mUsersDAO;
    List<User> mUsers;
    List<GradeCategory> mCategories;
    GradeCategoryDao gradeCategoryDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        login = findViewById(R.id.loginButton);
        signup = findViewById(R.id.signupButton);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent temp = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(temp);
            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent temp = new Intent(MainActivity.this, SignupActivity.class);
                startActivity(temp);
            }
        });

        mUsersDAO = Room.databaseBuilder(this, AppDatabase.class, AppDatabase.USER_TABLE)
                .allowMainThreadQueries()
                .build()
                .getUserDao();
        gradeCategoryDao = Room.databaseBuilder(this, AppDatabase.class, AppDatabase.GRADE_CATEGORY_TABLE)
                .allowMainThreadQueries()
                .build()
                .getGradeCategoryDao();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        basicSetup();
    }

    /**
     * Function that was used for beginning development
     * don't really need it anymore but helped when I was getting some errors
     */
    public void basicSetup(){
        mUsers = mUsersDAO.getAllUsers();
        mCategories = gradeCategoryDao.getAllCategories();
        if(mUsers.isEmpty()){
            User user = new User("admin","password","test", "name");
            mUsersDAO.insert(user);
        }
        if(mCategories.isEmpty()){
            GradeCategory one = new GradeCategory("Exams", 40.0, 1, "9/17/2020");
            GradeCategory two = new GradeCategory("Quizzes", 30.0, 2, "9/17/2020");
            GradeCategory three = new GradeCategory("Homework", 30.0, 3, "9/17/2020");
            gradeCategoryDao.insert(one);
            gradeCategoryDao.insert(two);
            gradeCategoryDao.insert(three);

        }
    }
}