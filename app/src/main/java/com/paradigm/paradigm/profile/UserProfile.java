package com.paradigm.paradigm.profile;

public class UserProfile {
    private String username;
    private boolean useDarkMode;
    private boolean newsFeedEnabled;
    private UserProgress userProgress;

    public UserProfile(String username) {
        this.username = username;
        resetSettings();
    }

    public void resetSettings() {
        useDarkMode = false;
        newsFeedEnabled = true;
    }

    public void clearUserData() {
        userProgress = new UserProgress();
    }
}
