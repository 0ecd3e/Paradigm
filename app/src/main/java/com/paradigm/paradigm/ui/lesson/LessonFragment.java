package com.paradigm.paradigm.ui.lesson;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.paradigm.paradigm.MainActivity;
import com.paradigm.paradigm.R;
import com.paradigm.paradigm.exercises.question.Question;
import com.paradigm.paradigm.profile.UserProgress;

import java.util.List;

public class LessonFragment extends Fragment {

    private LessonViewModel mViewModel;

    public static LessonFragment newInstance() {
        return new LessonFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_lesson, container, false);

        TextView lessonTitle = root.findViewById(R.id.lessonTitle);
        TextView lessonText = root.findViewById(R.id.lessonText);

        UserProgress userProgress = MainActivity.getUserProfile().getUserProgress();

        lessonTitle.setText(userProgress.getCurrentLesson().getName());
        lessonText.setText(userProgress.getCurrentLesson().getLessonContent());

        List<Question> questions = userProgress.getCurrentLesson().getQuestions();

        CardView q1 = root.findViewById(R.id.lessonQ1Button);
        q1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userProgress.setCurrentQuestion(questions.get(0));
                Navigation.findNavController(v).navigate(R.id.action_lessonFragment_to_MCQFragment);
            }
        });

        CardView q2 = root.findViewById(R.id.lessonQ2Button);
        q2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userProgress.setCurrentQuestion(questions.get(1));
                Navigation.findNavController(v).navigate(R.id.action_lessonFragment_to_FIBQuestionFragment);
            }
        });

        CardView q3 = root.findViewById(R.id.lessonQ3Button);
        q3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userProgress.setCurrentQuestion(questions.get(2));
                Navigation.findNavController(v).navigate(R.id.action_lessonFragment_to_MCQFragment);
            }
        });

        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(LessonViewModel.class);
        // TODO: Use the ViewModel
    }

    //find way to set question buttons green if answered correctly

}
