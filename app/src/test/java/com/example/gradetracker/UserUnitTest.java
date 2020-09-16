package com.example.gradetracker;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserUnitTest {
    User user;

    @Before
    public void createUser() {
        user = new User("username", "password", "first", "last");
    }

    @Test
    public void constructorTest() {
        assert (user != null);
    }

    @Test
    public void gettersAndSettersTest() {
        user.setUsername("test");
        user.setPassword("test");
        user.setFirstName("test");
        user.setLastName("test");

        assertEquals("test", user.getUsername());
        assertEquals("test", user.getPassword());
        assertEquals("test", user.getFirstName());
        assertEquals("test", user.getLastName());
    }
}
