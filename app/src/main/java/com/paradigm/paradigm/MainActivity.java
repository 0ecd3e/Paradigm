package com.paradigm.paradigm;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.preference.PreferenceManager;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        /*
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
         */
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_news, R.id.nav_checkpoint,
                R.id.nav_explore, R.id.nav_profile, R.id.settingsFragment)
                .setDrawerLayout(drawer)
                .build();
        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment);
        assert navHostFragment != null;
        NavController navController = navHostFragment.getNavController();
        //NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        PreferenceManager.setDefaultValues(this, R.xml.root_preferences, false);
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        String pref1 = String.valueOf(sharedPref.getBoolean("newsFeedSwitch", true));
        String pref2 = String.valueOf(sharedPref.getBoolean("darkModeSwitch", true));
        Toast.makeText(this, "News " + pref1, Toast.LENGTH_SHORT).show();
        Toast.makeText(this, "Dark " + pref2, Toast.LENGTH_SHORT).show();
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

    public void toCheckpoint(View view) {
        Toast.makeText(MainActivity.this, "toCheckpoint", Toast.LENGTH_LONG).show();
        Navigation.findNavController(view).navigate(R.id.nav_checkpoint);
    }

    public void toExplore(View view) {
        Toast.makeText(MainActivity.this, "toExplore", Toast.LENGTH_LONG).show();
        Navigation.findNavController(view).navigate(R.id.nav_explore);
    }

    public void toNews(View view) {
        Toast.makeText(MainActivity.this, "toNews", Toast.LENGTH_LONG).show();
        Navigation.findNavController(view).navigate(R.id.nav_news);
    }

    public void toLesson(View view) {
        Toast.makeText(MainActivity.this, "toLesson", Toast.LENGTH_LONG).show();
        //Navigation.findNavController(view).navigate(R.id.lessonFragment);
    }
}
