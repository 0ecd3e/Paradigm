package com.paradigm.paradigm.profile.progressEntries;

import java.util.ArrayList;
import java.util.List;

public class LessonProgress extends ProgressEntry {
    private List<QuestionProgress> questions;

    public LessonProgress() {
        super();
    }

    public LessonProgress(String componentName) {
        super(componentName, false);
        questions = new ArrayList<>();
    }

    public boolean checkComplete() {
        boolean notComplete = false;
        for (QuestionProgress questionProgress : questions) {
            if (!questionProgress.isComplete()) {
                clearProgress();
                notComplete = true;
                break;
            }
        }

        if (!notComplete) {
            setComplete();
        }

        return isComplete;
    }

    public void setQuestionProgress(QuestionProgress questionProgress) {
        int index = questions.indexOf(questionProgress);
        if (index == -1) {
            questions.add(questionProgress);
        } else {
            questions.set(index, questionProgress);
        }
    }

    public QuestionProgress getQuestionProgress(String questionName) {
        for (QuestionProgress questionProgress : questions) {
            if (questionProgress.componentName.equals(questionName)) {
                return questionProgress;
            }
        }
        return null;

    }
}
