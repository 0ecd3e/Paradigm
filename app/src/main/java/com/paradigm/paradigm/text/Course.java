package com.paradigm.paradigm.text;

import java.util.HashSet;
import java.util.Set;

public class Course extends Content {

    private Set<ContentModule> modules;

    public Course() {
        super();
    }

    public Course(String name) {
        super(name);
        modules = new HashSet<>();
    }

    @Override
    public String storedName() {
        return null;
    }

    public Set<ContentModule> getModules() {
        return modules;
    }

    public void addModule(ContentModule module) {
        modules.add(module);
    }

    public void removeModule(ContentModule module) {
        modules.remove(module);
    }

}
