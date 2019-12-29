package com.paradigm.paradigm.profile.progressEntries;

import com.paradigm.paradigm.text.ContentModule;

import java.util.Map;

public class CourseProgress extends ProgressEntry {
    private Map<String, ModuleProgress> modules;

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

    public void addModule(ContentModule contentModule) {
        ModuleProgress moduleProgress = new ModuleProgress();
        modules.put(contentModule.getName(), moduleProgress);
    }

    public void addModuleProgress(String moduleName, ModuleProgress moduleProgress) {
        modules.put(moduleName, moduleProgress);
    }

    public ModuleProgress getModuleProgress(String moduleName) {
        return modules.get(moduleName);
    }

}
