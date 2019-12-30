package com.paradigm.paradigm.profile.progressEntries;

import java.util.HashMap;
import java.util.Map;

public class CourseProgress extends ProgressEntry {
    private Map<String, ModuleProgress> modules;

    public CourseProgress() {
        super();
    }

    public CourseProgress(String name) {
        super(name, false);
        modules = new HashMap<>();
    }

    @Override
    public boolean isComplete() {
        boolean notComplete = false;
        for (ModuleProgress moduleProgress : modules.values()) {
            if (!moduleProgress.isComplete()) {
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

    public void setModuleProgress(String moduleName, ModuleProgress moduleProgress) {
        modules.put(moduleName, moduleProgress);
    }

    public ModuleProgress getModuleProgress(String moduleName) {
        return modules.get(moduleName);
    }

}
