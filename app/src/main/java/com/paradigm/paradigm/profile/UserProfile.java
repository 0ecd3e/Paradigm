package com.paradigm.paradigm.profile;

import java.io.Serializable;

public class UserProfile implements Serializable {
    private String username;
    private boolean useDarkMode;
    private boolean newsFeedEnabled;
    private UserProgress userProgress;

    public UserProfile(String username) {
        this.username = username;
        resetSettings();
        userProgress = new UserProgress();
    }

    public void resetSettings() {
        useDarkMode = false;
        newsFeedEnabled = true;
    }

    public void clearUserData() {
        userProgress = new UserProgress();
    }

    public UserProgress getUserProgress() {
        return userProgress;
    }

    public void setUserProgress(UserProgress userProgress) {
        this.userProgress = userProgress;
    }
}
