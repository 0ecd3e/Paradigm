package com.paradigm.paradigm.ui.profile;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.paradigm.paradigm.MainActivity;
import com.paradigm.paradigm.R;
import com.paradigm.paradigm.profile.UserProfile;
import com.paradigm.paradigm.profile.progressEntries.CourseProgress;

public class ProfileFragment extends Fragment {

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public ProfileFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static ProfileFragment newInstance(int columnCount) {
        ProfileFragment fragment = new ProfileFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        updateUsernameDisplay(view);

        ImageButton usernameEdit = view.findViewById(R.id.editUsernameButton);
        usernameEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (MainActivity.getUserProfile() == null) {
                    ((MainActivity) requireActivity()).initProfile();
                } else {
                    DialogFragment dialog = new ChangeUsernameDialog();
                    dialog.show(requireActivity().getSupportFragmentManager(), "UsernameChangeDialogFragment");
                }
            }
        });

        ProgressBar overallProgress = view.findViewById(R.id.profileOverallProgressBar);
        TextView overallProgressPercent = view.findViewById(R.id.profileOverallProgressPercent);
        UserProfile userProfile = ((MainActivity) requireActivity()).getUserProfile();
        if (userProfile == null) {
            ((MainActivity) requireActivity()).initProfile();
        } else {
            CourseProgress courseCompleteness = userProfile.getUserProgress().findCourseProgress(MainActivity.course.getName());
            int overallCompleteness = courseCompleteness.completePercentage();
            overallProgress.setProgress(overallCompleteness);
            String percentComplete = overallCompleteness + "%";
            overallProgressPercent.setText(percentComplete);


            RecyclerView recycler = view.findViewById(R.id.profileModuleProgressList);

            // Set the adapter
            if (recycler != null) {
                Context context = recycler.getContext();
                if (mColumnCount <= 1) {
                    recycler.setLayoutManager(new LinearLayoutManager(context));
                } else {
                    recycler.setLayoutManager(new GridLayoutManager(context, mColumnCount));
                }
                CourseProgress courseProgress = ((MainActivity) requireActivity())
                        .getUserProfile().getUserProgress().findCourseProgress(MainActivity.course.getName());
                recycler.setAdapter(new ProfileModuleRecyclerViewAdapter(courseProgress));
            }
        }
        return view;
    }


    public void updateUsernameDisplay(View view) {
        final TextView textView = view.findViewById(R.id.profileFragmentUsername);
        MainActivity mainActivity = (MainActivity) requireActivity();
        UserProfile userProfile = MainActivity.getUserProfile();
        if (userProfile == null) {
            mainActivity.initProfile();
        } else {
            textView.setText(userProfile.getUsername());
        }
    }
}
