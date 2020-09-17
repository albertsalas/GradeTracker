package com.example.gradetracker.DB;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.gradetracker.User;

import java.util.List;

@Dao
public interface UserDao {
    @Insert
    void insert(User... users);

    @Update
    void update(User... users);

    @Delete
    void delete(User... users);

    @Query("SELECT * FROM " + AppDatabase.USER_TABLE + " WHERE userID = :id")
    User getUser(int id);

    @Query("SELECT * FROM " + AppDatabase.USER_TABLE)
    List<User> getAllUsers();

    @Query("UPDATE " + AppDatabase.USER_TABLE + " SET username = :u")
    void updateUsername(String u);

    @Query("UPDATE " + AppDatabase.USER_TABLE + " SET password = :p")
    void updatePassword(String p);

    @Query("UPDATE " + AppDatabase.USER_TABLE + " SET firstName = :f")
    void updateFirstName(String f);

    @Query("UPDATE " + AppDatabase.USER_TABLE + " SET lastName = :l")
    void updateLastName(String l);
}
