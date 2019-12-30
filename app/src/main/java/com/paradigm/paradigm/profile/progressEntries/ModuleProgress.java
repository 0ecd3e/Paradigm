package com.paradigm.paradigm.profile.progressEntries;

import java.util.HashMap;
import java.util.Map;

public class ModuleProgress extends ProgressEntry {
    private Map<String, LessonProgress> lessons;
    private Map<String, QuestionProgress> questions;

    public ModuleProgress() {
        super();
    }

    public ModuleProgress(String componentName) {
        super(componentName, false);
        lessons = new HashMap<>();
        questions = new HashMap<>();
    }

    @Override
    public boolean isComplete() {
        boolean notComplete = false;
        for (LessonProgress lessonProgress : lessons.values()) {
            if (!lessonProgress.isComplete()) {
                clearProgress();
                notComplete = true;
                break;
            }
        }

        for (QuestionProgress questionProgress : questions.values()) {
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

    public void setLessonProgress(String lessonName, boolean isComplete) {
        LessonProgress lessonProgress = new LessonProgress(lessonName);
        lessonProgress.isComplete = isComplete;
        lessons.put(lessonName, lessonProgress);
    }

    public void setQuestionProgress(String questionName, boolean isComplete) {
        QuestionProgress questionProgress = new QuestionProgress(questionName);
        questionProgress.isComplete = isComplete;
        questions.put(questionName, questionProgress);
    }

    public boolean getQuestionProgress(String questionName) {
        QuestionProgress questionProgress = questions.get(questionName);
        return questionProgress.isComplete();
    }

    public boolean getLessonProgress(String lessonName) {
        LessonProgress lessonProgress = lessons.get(lessonName);
        return lessonProgress.isComplete();
    }
}
