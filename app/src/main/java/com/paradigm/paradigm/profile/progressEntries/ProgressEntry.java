package com.paradigm.paradigm.profile.progressEntries;

import java.io.Serializable;

public abstract class ProgressEntry implements Serializable {
    protected String componentName;
    protected boolean isComplete;

    public ProgressEntry() {
        super();
    }

    public ProgressEntry(String componentName, boolean isComplete) {
        this.componentName = componentName;
        this.isComplete = isComplete;
    }

    public String getComponentName() {
        return componentName;
    }

    public void setComponentName(String name) {
        this.componentName = name;
    }

    public void setComplete() {
        this.isComplete = true;
    }

    public void clearProgress() {
        this.isComplete = false;
    }

    public boolean isComplete() {
        return isComplete;
    }
}
