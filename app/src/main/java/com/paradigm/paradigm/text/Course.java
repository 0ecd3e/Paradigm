package com.paradigm.paradigm.text;

import com.fasterxml.jackson.databind.Module;

import java.util.HashSet;
import java.util.Set;

public class Course extends Content {

    private Set<Module> modules;

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

    public Set<Module> getModules() {
        return modules;
    }

    public boolean isCompleted() {
        return isComplete;
    }
}
