package com.paradigm.paradigm.profile;

import java.util.Map;

class ModuleProgress extends ProgressEntry {
    private Map<LessonProgress, LessonProgress> lessons;
    private Map<QuestionProgress, QuestionProgress> questions;

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
}
