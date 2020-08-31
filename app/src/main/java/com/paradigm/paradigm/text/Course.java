package com.paradigm.paradigm.text;

import androidx.annotation.NonNull;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.paradigm.paradigm.text.io.CourseDeserializer;
import com.paradigm.paradigm.text.io.CourseSerializer;

import java.util.ArrayList;
import java.util.List;

@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "@id")
@JsonSerialize(using = CourseSerializer.class)
@JsonDeserialize(using= CourseDeserializer.class)
public class Course extends Content {

    @JsonProperty("modules")
    private List<ContentModule> modules;

    public Course() {
        super();
    }

    public Course(String name) {
        super(name);
        modules = new ArrayList<>();
    }

    @Override
    public String descriptionPath() {
        return DIR_ROOT + name + "/" + DESC_FILE;
    }

    public List<ContentModule> getModules() {
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

    public ContentModule findContentModule(String moduleName) {
        for (ContentModule module : modules) {
            if (module.getName().equals(moduleName)) {
                return module;
            }
        }
        return null;
    }

    @NonNull
    @Override
    public String toString() {
        System.out.println(name);
        System.out.println(description);
        System.out.println(modules);
        return "Course";
    }

}
