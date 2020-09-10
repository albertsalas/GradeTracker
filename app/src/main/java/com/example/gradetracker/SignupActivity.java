package com.example.gradetracker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gradetracker.DB.AppDatabase;
import com.example.gradetracker.DB.UserDao;

import java.util.List;

public class SignupActivity extends AppCompatActivity {
    Button signup;
    EditText name;
    EditText username;
    EditText password;

    UserDao mUserDao;
    List<User> mUsers;

    String tempUsername;
    String tempPassword;
    String tempName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        name = findViewById(R.id.firstlastName);
        username = findViewById(R.id.usernameSignup);
        password = findViewById(R.id.passwordSignup);


        mUserDao = Room.databaseBuilder(this, AppDatabase.class, AppDatabase.USER_TABLE)
                .allowMainThreadQueries()
                .build()
                .getUserDao();

        insertInfo();
    }

    void insertInfo(){
        signup = findViewById(R.id.signupB);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sendToLogin = new Intent(SignupActivity.this, MainActivity.class);

                //Displays toast for empty fields
                if (username.getText().toString().equals("") || password.getText().toString().equals("")) {
                    Toast.makeText(SignupActivity.this, "One or more fields are blank", Toast.LENGTH_SHORT).show();
                }

                else{
                    mUsers = mUserDao.getAllUsers();
                    tempUsername = username.getText().toString();
                    tempPassword = password.getText().toString();
                    tempName = name.getText().toString();
                    String[] tempSplitName = tempName.split(" ");
                    String a = tempSplitName[0];
                    String b = tempSplitName[1];

                    if(!mUsers.isEmpty()){
                        StringBuilder stringBuilder = new StringBuilder();
                        for(User user : mUsers){
                            stringBuilder.append(user.getUsername());
                            System.out.println("USERNAME "+ user);

                            if(user.getUsername().equals(tempUsername)){
                                Toast.makeText(SignupActivity.this, "Name Taken", Toast.LENGTH_SHORT).show();
                                break;
                            }
                        }
                        if(!stringBuilder.toString().contains(tempUsername)) {
                            mUserDao.insert(new User(tempUsername, tempPassword, a, b));

                            //after new user is inserted grab it by username to put into logs
                            Toast.makeText(SignupActivity.this, "Account Created Successfully", Toast.LENGTH_SHORT).show();
                            startActivity(sendToLogin);
                        }
                    }


                }
            }
        });
    }

}