package com.paradigm.paradigm.text;

import java.util.HashSet;
import java.util.Set;

public class Course extends Content {

    private Set<ContentModule> modules;

    public Course(String name) {
        super(name);
        modules = new HashSet<>();
    }

    @Override
    public String getFileName() {
        return null;
    }

    public String getName() {
        return name;
    }

    public Set<ContentModule> getModules() {
        return modules;
    }

    public boolean isCompleted() {
        return isComplete;
    }
}
