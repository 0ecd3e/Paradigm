package com.paradigm.paradigm.ui.question;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.paradigm.paradigm.MainActivity;
import com.paradigm.paradigm.R;
import com.paradigm.paradigm.exercises.question.Question;
import com.paradigm.paradigm.profile.UserProgress;
import com.paradigm.paradigm.profile.progressEntries.CourseProgress;
import com.paradigm.paradigm.profile.progressEntries.ModuleProgress;
import com.paradigm.paradigm.profile.progressEntries.QuestionProgress;

import static com.paradigm.paradigm.MainActivity.course;

public class MCQFragment extends Fragment {

    View root;

    public static MCQFragment newInstance() {
        return new MCQFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.m_c_q_fragment, container, false);
        updateQuestionProgress(root);

        UserProgress userProgress = MainActivity.getUserProfile().getUserProgress();
        TextView questionTitle = root.findViewById(R.id.MCQTitle);
        questionTitle.setText(userProgress.getCurrentQuestion().getQuestionName());
        TextView questionText = root.findViewById(R.id.MCQContent);
        questionText.setText(userProgress.getCurrentQuestion().getQuestionText());

        CardView cardView = root.findViewById(R.id.checkMCQAnswer);
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onRadioButtonClicked(root.findViewById(R.id.MCQChoices));
            }
        });

        return root;
    }

    private void updateQuestionProgress(View root) {
        UserProgress userProgress = MainActivity.getUserProfile().getUserProgress();
        QuestionProgress questionProgress = userProgress.getQuestionProgress(userProgress.getCurrentQuestion());
        if (questionProgress.isComplete()) {
            TextView text = root.findViewById(R.id.MCQCompleteText);
            String answerKey = "You have correctly answered this question before.\n" + "Correct answer: " + userProgress.getCurrentQuestion().getAnswer().getAnswer();
            text.setText(answerKey);
            text.setTextColor(getResources().getColor(R.color.white));
            CardView indicator = root.findViewById(R.id.MCQCompleteIndicator);
            indicator.setCardBackgroundColor(getResources().getColor(R.color.cardGreen));
        }
        userProgress.isLessonComplete(userProgress.getCurrentLesson());
        CourseProgress courseProgress = userProgress.findCourseProgress(course.getName());
        String moduleName = course.getName() + "," + userProgress.getCurrentModule().getName();
        ModuleProgress moduleProgress = courseProgress.getModuleProgress(moduleName);
        moduleProgress.checkComplete();
        ((MainActivity) requireActivity()).saveProgress();
    }

    public void onRadioButtonClicked(View view) {
        Question question = MainActivity.getUserProfile().getUserProgress().getCurrentQuestion();
        RadioGroup radioGroup = (RadioGroup) view;
        RadioButton selection = radioGroup.findViewById(
                radioGroup.getCheckedRadioButtonId());
        if (selection != null) {
            String selectedText = (String) selection.getText();
            handleAnswer(question, selectedText.substring(0, 1));
        }
    }

    private void handleAnswer(Question question, String choice) {
        if (!question.checkAnswer(choice, question.getAnswer(),
                MainActivity.getUserProfile().getUserProgress())) {
            Toast.makeText(requireContext(), "Try another answer.", Toast.LENGTH_SHORT).show();
        }
        updateQuestionProgress(root);
    }

}
