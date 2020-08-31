package com.paradigm.paradigm.profile.progressEntries;

import java.util.ArrayList;
import java.util.List;

public class ModuleProgress extends ProgressEntry {
    private List<LessonProgress> lessons;

    public ModuleProgress() {
        super();
    }

    public ModuleProgress(String componentName) {
        super(componentName, false);
        lessons = new ArrayList<>();
    }

    public boolean checkComplete() {
        boolean notComplete = false;
        for (LessonProgress lessonProgress : lessons) {
            if (!lessonProgress.isComplete()) {
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

    public void setLessonProgress(LessonProgress lessonProgress) {
        int index = lessons.indexOf(lessonProgress);
        if (index == -1) {
            lessons.add(lessonProgress);
        } else {
            lessons.set(index, lessonProgress);
        }
    }


    public LessonProgress getLessonProgress(String lessonName) {
        for (LessonProgress lessonProgress : lessons) {
            if (lessonProgress.componentName.equals(lessonName)) {
                return lessonProgress;
            }
        }
        return null;
    }

    public int completePercentage() {
        int count = 0;

        for (LessonProgress lessonProgress : lessons) {
            if (lessonProgress.isComplete()) {
                count++;
            }
        }

        double total = (double) count / lessons.size();
        return (int) (total * 100);
    }
}
