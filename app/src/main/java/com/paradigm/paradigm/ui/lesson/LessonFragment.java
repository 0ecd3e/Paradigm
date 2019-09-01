package com.paradigm.paradigm.ui.lesson;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.paradigm.paradigm.R;
import com.paradigm.paradigm.ui.lessonContent.LessonContentFragment;

public class LessonFragment extends Fragment {

    private LessonViewModel mViewModel;

    public static LessonFragment newInstance() {
        return new LessonFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.lesson_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(LessonViewModel.class);
        // TODO: Use the ViewModel
    }

    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        Button button = (Button) view.findViewById(R.id.toContent);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Navigation.findNavController(view).navigate(R.id.lessonContentFragment);
                NavController navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment);
                navController.navigate(R.id.action_lessonFragment_to_lessonContentFragment);

            }
        });
    }

}