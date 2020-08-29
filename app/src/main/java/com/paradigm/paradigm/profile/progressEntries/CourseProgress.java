package com.paradigm.paradigm.profile.progressEntries;

import java.util.ArrayList;
import java.util.List;

public class CourseProgress extends ProgressEntry {
    private List<ModuleProgress> modules;

    public CourseProgress() {
        super();
    }

    public CourseProgress(String name) {
        super(name, false);
        modules = new ArrayList<>();
    }

    public boolean checkComplete() {
        boolean notComplete = false;
        for (ModuleProgress moduleProgress : modules) {
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

    public void setModuleProgress(ModuleProgress moduleProgress) {
        int index = modules.indexOf(moduleProgress);
        if (index == -1) {
            modules.add(moduleProgress);
        } else {
            modules.set(index, moduleProgress);
        }
    }

    public ModuleProgress getModuleProgress(String moduleName) {
        for (ModuleProgress moduleProgress : modules) {
            if (moduleProgress.componentName.equals(moduleName)) {
                return moduleProgress;
            }
        }
        return null;
    }

}
