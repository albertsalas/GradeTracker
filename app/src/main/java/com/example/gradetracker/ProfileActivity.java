package com.example.gradetracker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.gradetracker.DB.AppDatabase;
import com.example.gradetracker.DB.UserDao;

public class ProfileActivity extends AppCompatActivity {

    EditText editUsername;
    EditText editPassword;
    EditText editFirstName;
    EditText editLastName;
    Button saveProfile;

    UserDao userDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        setTitle("Profile");

        editUsername = findViewById(R.id.editUsername);
        editPassword = findViewById(R.id.editPassword);
        editFirstName = findViewById(R.id.editFirstName);
        editLastName = findViewById(R.id.editLastName);

        userDao = Room.databaseBuilder(this, AppDatabase.class, AppDatabase.USER_TABLE)
                .allowMainThreadQueries()
                .build()
                .getUserDao();

        updateProfile();
    }

    private void updateProfile() {
        saveProfile = findViewById(R.id.saveProfile);
        saveProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = editUsername.getText().toString().trim();
                String password = editPassword.getText().toString().trim();
                String firstName = editFirstName.getText().toString().trim();
                String lastName = editLastName.getText().toString().trim();

                if (username.isEmpty() && password.isEmpty() && firstName.isEmpty() && lastName.isEmpty()) {
                    Toast.makeText(ProfileActivity.this, "No updates to make!", Toast.LENGTH_LONG).show();
                } else {
                    if (!username.isEmpty()) {
                        userDao.updateUsername(username);
                    }

                    if (!password.isEmpty()) {
                        userDao.updatePassword(password);
                    }

                    if (!firstName.isEmpty()) {
                        userDao.updateFirstName(firstName);
                    }

                    if (!lastName.isEmpty()) {
                        userDao.updateLastName(lastName);
                    }

                    Toast.makeText(ProfileActivity.this, "Your profile was updated!", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}