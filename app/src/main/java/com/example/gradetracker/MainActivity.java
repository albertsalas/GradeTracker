package com.example.gradetracker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.gradetracker.DB.AppDatabase;
import com.example.gradetracker.DB.CourseDao;
import com.example.gradetracker.DB.EnrollmentDao;
import com.example.gradetracker.DB.UserDao;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    Button login;
    Button signup;
    UserDao mUsersDAO;
    List<User> mUsers;
    CourseDao courseDao;
    List<Course> mCourses;
    EnrollmentDao enrollmentDao;
    List<Enrollment> enrollment;

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
        courseDao = Room.databaseBuilder(this, AppDatabase.class, AppDatabase.COURSE_TABLE)
                .allowMainThreadQueries()
                .build()
                .getCourseDao();
        enrollmentDao = Room.databaseBuilder(this, AppDatabase.class, AppDatabase.COURSE_TABLE)
                .allowMainThreadQueries()
                .build()
                .getEnrollmentDao();

//        enrollment = enrollmentDao.getAllEnrollments();
//        for(Enrollment en : enrollment){
//            enrollmentDao.delete(en);
//        }
//        mCourses = courseDao.getAllCourses();
//
//        for(Course course : mCourses){
//            courseDao.delete(course);
//        }
//        mUsers = mUsersDAO.getAllUsers();
//        for(User user : mUsers){
//            mUsersDAO.delete(user);
//
//        }
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        basicSetup();
    }

    public void basicSetup(){
        mUsers = mUsersDAO.getAllUsers();

        if(mUsers.isEmpty()){
            User user = new User("admin","password","test", "name");
            //user.setUserID(1);
            mUsersDAO.insert(user);
        }
    }
}