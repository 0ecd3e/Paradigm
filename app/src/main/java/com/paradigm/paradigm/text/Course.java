package com.paradigm.paradigm.text;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.paradigm.paradigm.text.io.CourseSerializer;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "@id")
@JsonSerialize(using = CourseSerializer.class)
public class Course extends Content {

    @JsonProperty("modules")
    private Map<String, ContentModule> modules;

    public Course() {
        super();
    }

    public Course(String name) {
        super(name);
        modules = new HashMap<>();
    }

    @Override
    public String descriptionPath() {
        return DIR_ROOT + name + "/" + DESC_FILE;
    }

    public Map<String, ContentModule> getModules() {
        return modules;
    }

    public Collection<ContentModule> getModuleList() {
        return modules.values();
    }

    public void addModule(ContentModule module) {
        modules.put(module.getName(), module);
        module.setParentCourse(name);
    }

    public void removeModule(ContentModule module) {
        modules.remove(module.getName());
        module.clearParentCourse();
    }

    public void setParents() {
        for (ContentModule module : modules.values()) {
            module.setParents();
        }
    }

}
