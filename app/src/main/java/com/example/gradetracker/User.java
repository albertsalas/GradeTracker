package com.example.gradetracker;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.gradetracker.DB.AppDatabase;

/**
 * User is a class used to interact with the user database
 * @author Albert
 * @version 1.0
 */
@Entity(tableName = AppDatabase.USER_TABLE)
public class User {

    @PrimaryKey(autoGenerate = true)
    private int userID;
    private String username;
    private String password;
    private String firstName;
    private String lastName;

    /**
     * @param username for the users login name
     * @param password for the users login password
     * @param firstName user's general info
     * @param lastName user's general info
     */
    public User(String username, String password, String firstName, String lastName) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    /**
     * getUserID for getting the current User class ID
     * @return userID is the ID that's returned
     */
    public int getUserID() {
        return userID;
    }

    /**
     * setUserID is for setting the userID
     * @param userID is the new userID that the method takes in
     */
    public void setUserID(int userID) {
        this.userID = userID;
    }

    /**
     * Method for returning username
     * @return the current user's username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Method for setting username
     * @param username is the new username that will be set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Method for getting current user password
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Method for setting password
     * @param password is the new password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Method for getting the user's first name
     * @return first name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Method for setting user's first name
     * @param firstName is the new first name for the user
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Method for getting user's last name
     * @return last name
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Method for setting user's last name
     * @param lastName is the new last name the user wants used
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
