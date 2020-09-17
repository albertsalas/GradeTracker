package com.example.gradetracker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.gradetracker.DB.AppDatabase;
import com.example.gradetracker.DB.UserDao;

import java.util.List;

public class LoginActivity extends AppCompatActivity {
    Button login;
    EditText username;
    EditText password;
    UserDao mUserDao;
    List<User> mUsers;
    String tempUsername;
    String tempPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username = findViewById(R.id.usernameLogin);
        password = findViewById(R.id.passwordLogin);

        mUserDao = Room.databaseBuilder(this, AppDatabase.class, AppDatabase.USER_TABLE)
                .allowMainThreadQueries()
                .build()
                .getUserDao();

        submitLoginInfo();
    }

    public void submitLoginInfo(){
        login = findViewById(R.id.loginB);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sendToSchedule = new Intent(LoginActivity.this, ClassScheduleActivity.class);

                mUsers = mUserDao.getAllUsers();
                tempUsername = username.getText().toString().trim();
                tempPassword = password.getText().toString().trim();

                if(username.getText().toString().equals("") || password.getText().toString().equals("")){
                    Toast.makeText(LoginActivity.this, "One or more fields are blank", Toast.LENGTH_SHORT).show();
                }
                else if(!mUsers.isEmpty()){
                    StringBuilder stringBuilder = new StringBuilder();

                    for(User user : mUsers){
                       // System.out.println(user.getUsername() +" "+ user.getUserID());
                        stringBuilder.append(user);

                        if(user.getUsername().equals(tempUsername) && user.getPassword().equals(tempPassword)){
                            Toast.makeText(LoginActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                            sendToSchedule.putExtra("username", tempUsername);
                            sendToSchedule.putExtra("uID", user.getUserID());
                            startActivity(sendToSchedule);
                        }
                    }
                }
            }
        });
    }
}