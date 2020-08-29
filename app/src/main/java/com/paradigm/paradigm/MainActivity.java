package com.paradigm.paradigm;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.DialogFragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.preference.PreferenceManager;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.android.material.navigation.NavigationView;
import com.paradigm.paradigm.newsfeed.Feed;
import com.paradigm.paradigm.profile.UserProfile;
import com.paradigm.paradigm.text.Course;
import com.paradigm.paradigm.ui.profile.CreateUserDialog;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class MainActivity extends AppCompatActivity
        implements CreateUserDialog.NoticeDialogListener {
    private AppBarConfiguration mAppBarConfiguration;
    private UserProfile userProfile = null;
    private SharedPreferences sharedPreferences;
    public static String feedURL = "https://rss.cbc.ca/lineup/technology.xml";
    private Course course;
    private Feed newsFeed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        PreferenceManager.setDefaultValues(this, R.xml.root_preferences, false);
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        String pref1 = String.valueOf(sharedPreferences.getBoolean("newsFeedSwitch", true));
        String pref2 = String.valueOf(sharedPreferences.getBoolean("darkModeSwitch", true));

        Toast.makeText(this, "News " + pref1 + "\nDark " + pref2, Toast.LENGTH_SHORT).show();

        if (sharedPreferences.getBoolean("newsFeedSwitch", true)) {
            setContentView(R.layout.activity_main);
        } else {
            setContentView(R.layout.activity_main_newsdisabled);
        }

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        /*
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_SHORT)
                        .setAction("Action", null).show();
            }
        });
         */

        DrawerLayout drawer;
        NavigationView navigationView;

        if (sharedPreferences.getBoolean("newsFeedSwitch", true)) {
            drawer = findViewById(R.id.drawer_layout);
            navigationView = findViewById(R.id.nav_view);
            // Passing each menu ID as a set of Ids because each
            // menu should be considered as top level destinations.
            mAppBarConfiguration = new AppBarConfiguration.Builder(
                    R.id.nav_home, R.id.nav_news,
                    R.id.nav_explore, R.id.nav_profile, R.id.nav_settings)
                    .setDrawerLayout(drawer)
                    .build();
            newsFeed = new Feed();
        } else {
            drawer = findViewById(R.id.drawer_layout_newsdisabled);
            navigationView = findViewById(R.id.nav_view_newsdisabled);
            // Passing each menu ID as a set of Ids because each
            // menu should be considered as top level destinations.
            mAppBarConfiguration = new AppBarConfiguration.Builder(
                    R.id.nav_home, R.id.nav_explore, R.id.nav_profile, R.id.nav_settings)
                    .setDrawerLayout(drawer)
                    .build();
        }
        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment);
        assert navHostFragment != null;
        NavController navController = navHostFragment.getNavController();
        //NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        initProfile();

        initContent();
    }

    private void initContent() {
        ObjectMapper objectMapper = new ObjectMapper();
        AssetManager assetManager = getAssets();
        try {
            InputStream inputStream = assetManager.open("courses/java/courseJava.json");
            course = objectMapper.readValue(inputStream, Course.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void initProfile() {
        try {
            FileInputStream fis = this.openFileInput("userProfile.ser");
            ObjectInputStream objectInputStream = new ObjectInputStream(fis);
            userProfile = (UserProfile) objectInputStream.readObject();
            objectInputStream.close();
            fis.close();
        } catch (IOException | ClassNotFoundException e) {
            // Error occurred when opening raw file for reading.
            // Create an instance of the dialog fragment and show it
            DialogFragment dialog = new CreateUserDialog();
            dialog.setCancelable(false);
            dialog.show(getSupportFragmentManager(), "NoticeDialogFragment");
            //dialog.setCanceledOnTouchOutside(false);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    public void toExplore(View view) {
        Toast.makeText(MainActivity.this, "toExplore", Toast.LENGTH_SHORT).show();
        Navigation.findNavController(view).navigate(R.id.action_nav_home_to_nav_explore);
    }

    public void toNews(View view) {
        Toast.makeText(MainActivity.this, "toNews", Toast.LENGTH_SHORT).show();
        Navigation.findNavController(view).navigate(R.id.action_nav_home_to_nav_news);
    }

    public void toProfile(View view) {
        Toast.makeText(MainActivity.this, "toProfile", Toast.LENGTH_SHORT).show();
        Navigation.findNavController(view).navigate(R.id.action_nav_home_to_nav_profile);
    }

    public void toLesson(View view) {
        Toast.makeText(MainActivity.this, "toLesson", Toast.LENGTH_SHORT).show();
        Navigation.findNavController(view).navigate(R.id.action_moduleFragment_to_lessonFragment);
    }

    public void toModuleFromExplore(View view) {
        Toast.makeText(MainActivity.this, "toModule", Toast.LENGTH_SHORT).show();
        Navigation.findNavController(view).navigate(R.id.action_nav_explore_to_moduleFragment);
    }


    public void toModuleFromHome(View view) {
        Toast.makeText(MainActivity.this, "toModule", Toast.LENGTH_SHORT).show();
        Navigation.findNavController(view).navigate(R.id.action_nav_home_to_moduleFragment);
    }

    public void toMCQ(View view) {
        Toast.makeText(MainActivity.this, "toMCQ", Toast.LENGTH_SHORT).show();
        Navigation.findNavController(view).navigate(R.id.action_lessonFragment_to_MCQFragment);
    }

    public void toFIBQ(View view) {
        Toast.makeText(MainActivity.this, "toFIBQ", Toast.LENGTH_SHORT).show();
        Navigation.findNavController(view).navigate(R.id.action_lessonFragment_to_FIBQuestionFragment);
    }

    @Override
    public void onDialogPositiveClick(String username) {
        if (username.equals("")) {
            userProfile = new UserProfile("defaultUser");
        } else {
            userProfile = new UserProfile(username);
        }

        try (FileOutputStream fos = this.openFileOutput("userProfile.ser", Context.MODE_PRIVATE)) {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fos);
            objectOutputStream.writeObject(userProfile);
            objectOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public UserProfile getUserProfile() {
        return userProfile;
    }

    public void setUserProfile(UserProfile userProfile) {
        this.userProfile = userProfile;
    }

    public void editUsername(View view) {
        DialogFragment dialog = new CreateUserDialog();
        dialog.show(getSupportFragmentManager(), "NoticeDialogFragment");
    }

    public void displayToast(View view) {
        Toast.makeText(this, "DISPLAYTOAST", Toast.LENGTH_SHORT).show();
    }


    public void fibToast(android.view.View view) {
        Toast.makeText(this, "FIBTOAST", Toast.LENGTH_SHORT).show();
    }


    public void mcqToast(android.view.View view) {
        Toast.makeText(this, "MCQTOAST", Toast.LENGTH_SHORT).show();
    }

    public Course getCourse() {
        return course;
    }

    public Feed getNewsFeed() {
        return newsFeed;
    }
}
