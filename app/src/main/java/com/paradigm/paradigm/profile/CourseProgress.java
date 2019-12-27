package com.paradigm.paradigm.profile;

import java.util.Map;

public class CourseProgress extends ProgressEntry {
    private Map<ModuleProgress, ModuleProgress> modules;

    public CourseProgress() {
        super();
    }

    public CourseProgress(String name) {
        super(name, false);
    }

    @Override
    public boolean isComplete() {
        for (ModuleProgress moduleProgress : modules.values()) {
            if (!moduleProgress.isComplete()) {
                clearProgress();
            }
        }

        setComplete();
        return isComplete;
    }

}
