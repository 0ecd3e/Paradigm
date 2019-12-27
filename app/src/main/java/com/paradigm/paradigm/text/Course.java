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
    public String descriptionPath() {
        return "courses/" + name + "/description.txt";
    }

    public Set<ContentModule> getModules() {
        return modules;
    }

    public void addModule(ContentModule module) {
        modules.add(module);
        module.setParentCourse(name);
    }

    public void removeModule(ContentModule module) {
        modules.remove(module);
        module.clearParentCourse();
    }

    public void setParents() {
        for (ContentModule module : modules) {
            module.setParents();
        }
    }

}
