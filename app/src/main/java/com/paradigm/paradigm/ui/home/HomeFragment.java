package com.paradigm.paradigm.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.paradigm.paradigm.MainActivity;
import com.paradigm.paradigm.R;
import com.paradigm.paradigm.profile.UserProfile;
import com.paradigm.paradigm.profile.UserProgress;
import com.paradigm.paradigm.profile.progressEntries.CourseProgress;
import com.paradigm.paradigm.profile.progressEntries.ModuleProgress;
import com.paradigm.paradigm.text.ContentModule;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    View root;
    boolean loaded = false;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        ((MainActivity) requireActivity()).setHomeDataLoadedListener(new MainActivity.HomeDataLoadedListener() {
            @Override
            public void onHomePageLoaded() {
                updateCheckpoint();
                loaded = true;
            }
        });


        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);
        root = inflater.inflate(R.layout.fragment_home, container, false);

        if (loaded) {
            updateCheckpoint();
        }

        ImageView imageView = root.findViewById(R.id.latestModuleCardImage);
        imageView.setImageResource(R.drawable.smptebars);

        //final TextView textView = root.findViewById(R.id.text_home);
        /*
        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                //textView.setText(s);
            }
        });
        */

        ((MainActivity) requireActivity()).listenerSet();
        return root;
    }

    public void updateCheckpoint() {
        ContentModule contentModule = UserProgress.getCheckpointModule();
        TextView title = root.findViewById(R.id.latestModuleTitle);
        title.setText(contentModule.getName());
        TextView desc = root.findViewById(R.id.latestModuleDescription);
        desc.setText(contentModule.getDescription());

        String courseName = MainActivity.course.getName();
        String moduleName = courseName + "," + contentModule.getName();
        UserProfile userProfile = ((MainActivity) requireActivity()).getUserProfile();

        if (userProfile == null) {
            ((MainActivity) requireActivity()).initProfile();
        } else {
            CourseProgress courseProgress = userProfile.getUserProgress().findCourseProgress(courseName);
            ModuleProgress moduleProgress = courseProgress.getModuleProgress(moduleName);

            ProgressBar progressBar = root.findViewById(R.id.latestModuleProgressBar);
            progressBar.setProgress(moduleProgress.completePercentage());
            TextView progressPercent = root.findViewById(R.id.latestModuleProgressPercent);
            String progresspercentage = moduleProgress.completePercentage() + "%";
            progressPercent.setText(progresspercentage);
        }

        root.findViewById(R.id.latestModuleCard).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContentModule contentModule1 = ((MainActivity) requireActivity()).getCourse().findContentModule(contentModule.getName());
                UserProgress.setCurrentModule(contentModule1);
                Navigation.findNavController(v).navigate(R.id.action_nav_home_to_moduleFragment);
            }
        });
    }

}
