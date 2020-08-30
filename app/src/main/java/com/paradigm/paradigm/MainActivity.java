package com.paradigm.paradigm;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.AssetManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;
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
import com.paradigm.paradigm.text.ContentModule;
import com.paradigm.paradigm.text.Course;
import com.paradigm.paradigm.text.Lesson;
import com.paradigm.paradigm.text.io.ContentLoader;
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
    public String feedURL;
    public static Course course;
    SharedPreferences.OnSharedPreferenceChangeListener listener = new SharedPreferences.OnSharedPreferenceChangeListener() {
        @Override
        public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
            if (key.equals("defaultFeedLocation")) {
                String oldLocation = feedURL;
                feedURL = sharedPreferences.getString("defaultFeedLocation", oldLocation);
            }
        }
    };
    private Feed newsFeed = new Feed();
    private static ContentModule currentModule;
    private static Lesson currentLesson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        PreferenceManager.setDefaultValues(this, R.xml.root_preferences, false);
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        String feedSource = sharedPreferences.getString("defaultFeedLocation", "https://rss.cbc.ca/lineup/technology.xml");
        feedURL = feedSource;
        sharedPreferences.registerOnSharedPreferenceChangeListener(listener);

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

        initContent();
        initProfile();
    }

    public static ContentModule getCurrentModule() {
        return currentModule;
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
        Navigation.findNavController(view).navigate(R.id.action_nav_home_to_nav_explore);
    }

    public void toNews(View view) {
        Navigation.findNavController(view).navigate(R.id.action_nav_home_to_nav_news);
    }

    public void toProfile(View view) {
        Navigation.findNavController(view).navigate(R.id.action_nav_home_to_nav_profile);
    }

    public void toLesson(View view) {
        Navigation.findNavController(view).navigate(R.id.action_moduleFragment_to_lessonFragment);
    }

    public void toModuleFromExplore(View view) {
        Navigation.findNavController(view).navigate(R.id.action_nav_explore_to_moduleFragment);
    }


    public void toModuleFromHome(View view) {
        Navigation.findNavController(view).navigate(R.id.action_nav_home_to_moduleFragment);
    }

    public void toMCQ(View view) {
        Navigation.findNavController(view).navigate(R.id.action_lessonFragment_to_MCQFragment);
    }

    public void toFIBQ(View view) {
        Navigation.findNavController(view).navigate(R.id.action_lessonFragment_to_FIBQuestionFragment);
    }

    @Override
    public void onDialogPositiveClick(String username) {
        if (username.equals("")) {
            userProfile = new UserProfile("defaultUser");
        } else {
            userProfile = new UserProfile(username);
        }

        userProfile.getUserProgress().addCourse(course);

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

    public void visitNewsArticle(View v) {
        Intent i = new Intent(Intent.ACTION_VIEW);
        TextView textView = v.findViewById(R.id.newsArticleUrl);
        String url = textView.getText().toString();
        i.setData(Uri.parse(url));
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        this.startActivity(i);
    }

    public String getFeedURL() {
        return feedURL;
    }

    public static void setCurrentModule(ContentModule cm) {
        currentModule = cm;
    }

    public static Lesson getCurrentLesson() {
        return currentLesson;
    }

    public static void setCurrentLesson(Lesson cl) {
        currentLesson = cl;
    }

    private void initContent() {
        ObjectMapper objectMapper = new ObjectMapper();
        AssetManager assetManager = getAssets();
        try {
            InputStream inputStream = assetManager.open("courses/java/courseJava.json");
            course = objectMapper.readValue(inputStream, Course.class);
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        ContentLoader contentLoader = new ContentLoader();
        contentLoader.loadDescription(course, assetManager);

        for (ContentModule module : course.getModules()) {
            contentLoader.loadDescription(module, assetManager);
            for (Lesson lesson : module.getLessons()) {
                contentLoader.loadDescription(lesson, assetManager);
                contentLoader.loadLessonContent(lesson, assetManager);
                //contentLoader.loadQuestions(lesson, assetManager);
                lesson.setParents();
            }
        }

    }
}
