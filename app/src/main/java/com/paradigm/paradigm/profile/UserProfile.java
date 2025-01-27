package com.paradigm.paradigm.profile;

import java.io.Serializable;

public class UserProfile implements Serializable {
    private String username;
    private UserProgress userProgress;

    public UserProfile(String username) {
        this.username = username;
        userProgress = new UserProgress();
    }

    public UserProgress getUserProgress() {
        return userProgress;
    }

    public void setUserProgress(UserProgress userProgress) {
        this.userProgress = userProgress;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String s) {
        username = s;
    }
}
