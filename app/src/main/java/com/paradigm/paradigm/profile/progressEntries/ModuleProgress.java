package com.paradigm.paradigm.profile.progressEntries;

import java.util.Map;

public class ModuleProgress extends ProgressEntry {
    private Map<String, LessonProgress> lessons;
    private Map<String, QuestionProgress> questions;

    public ModuleProgress() {
        super();
    }

    @Override
    public boolean isComplete() {
        for (LessonProgress lessonProgress : lessons.values()) {
            if (!lessonProgress.isComplete()) {
                clearProgress();
            }
        }

        for (QuestionProgress questionProgress : questions.values()) {
            if (!questionProgress.isComplete()) {
                clearProgress();
            }
        }

        setComplete();
        return isComplete;
    }

    public void setLessonProgress(String lessonName, boolean isComplete) {
        LessonProgress lessonProgress = new LessonProgress();
        lessonProgress.isComplete = isComplete;
        lessons.put(lessonName, lessonProgress);
    }

    public void setQuestionProgress(String questionName, boolean isComplete) {
        QuestionProgress questionProgress = new QuestionProgress();
        questionProgress.isComplete = isComplete;
        questions.put(questionName, questionProgress);
    }

    public boolean getQuestionProgress(String questionName) {
        QuestionProgress questionProgress = questions.get(questionName);
        return questionProgress.isComplete();
    }
}
