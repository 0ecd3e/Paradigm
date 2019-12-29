package com.paradigm.paradigm.profile.progressEntries;

import java.util.Objects;

public abstract class ProgressEntry {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProgressEntry that = (ProgressEntry) o;
        return Objects.equals(componentName, that.componentName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(componentName);
    }
}
