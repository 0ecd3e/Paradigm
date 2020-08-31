package com.paradigm.paradigm.ui.question;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.paradigm.paradigm.MainActivity;
import com.paradigm.paradigm.R;
import com.paradigm.paradigm.exercises.question.Question;
import com.paradigm.paradigm.profile.UserProgress;
import com.paradigm.paradigm.profile.progressEntries.CourseProgress;
import com.paradigm.paradigm.profile.progressEntries.ModuleProgress;
import com.paradigm.paradigm.profile.progressEntries.QuestionProgress;

import static com.paradigm.paradigm.MainActivity.course;

public class FIBQuestionFragment extends Fragment {

    private FIBQuestionViewModel mViewModel;

    public static FIBQuestionFragment newInstance() {
        return new FIBQuestionFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.f_i_b_question_fragment, container, false);

        updateQuestionProgress(root);

        TextView questionTitle = root.findViewById(R.id.fibQuestionTitle);
        questionTitle.setText(MainActivity.getCurrentQuestion().getQuestionName());
        TextView questionText = root.findViewById(R.id.fibQuestionContent);
        questionText.setText(MainActivity.getCurrentQuestion().getQuestionText());

        CardView cardView = root.findViewById(R.id.checkFIBAnswer);
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Question question = MainActivity.getCurrentQuestion();
                EditText answer = root.findViewById(R.id.fibQuestionEntry);
                String parsedAnswer;
                if (answer == null) {
                    parsedAnswer = "defaultAnswer";
                } else {
                    parsedAnswer = answer.getText().toString();
                }
                question.checkAnswer(parsedAnswer, question.getAnswer(),
                        ((MainActivity) requireActivity()).getUserProfile().getUserProgress());
                updateQuestionProgress(root);
            }
        });
        return root;
    }

    private void updateQuestionProgress(View root) {
        UserProgress userProgress = ((MainActivity) requireActivity()).getUserProfile().getUserProgress();
        QuestionProgress questionProgress = userProgress.getQuestionProgress(MainActivity.getCurrentQuestion());
        if (questionProgress.isComplete()) {
            TextView text = root.findViewById(R.id.FIBQIndicatorText);
            String answerKey = "You have correctly answered this question before.\n" + "Correct answer: " + MainActivity.getCurrentQuestion().getAnswer().getAnswer();
            text.setText(answerKey);
            CardView indicator = root.findViewById(R.id.FIBQProgressIndicator);
            indicator.setCardBackgroundColor(0xff00ff00);
        }
        MainActivity mainActivity = (MainActivity) requireActivity();
        mainActivity.getUserProfile().getUserProgress().isLessonComplete(MainActivity.getCurrentLesson());
        CourseProgress courseProgress = userProgress.findCourseProgress(course.getName());
        String moduleName = course.getName() + "," + MainActivity.getCurrentModule().getName();
        ModuleProgress moduleProgress = courseProgress.getModuleProgress(moduleName);
        moduleProgress.checkComplete();
        mainActivity.saveProgress();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(FIBQuestionViewModel.class);
        // TODO: Use the ViewModel
    }

}
