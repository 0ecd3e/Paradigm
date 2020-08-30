package com.paradigm.paradigm.text;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import java.util.ArrayList;
import java.util.List;

@JsonIdentityInfo(generator= ObjectIdGenerators.IntSequenceGenerator.class, property="@id")
public class ContentModule extends Content {

    @JsonProperty("lessons")
    private List<Lesson> lessons;
    @JsonProperty("parentCourse")
    private String parentCourse;

    public ContentModule() {
        super();
    }

    public ContentModule(String name) {
        super(name);
        lessons = new ArrayList<>();
    }

    @Override
    public String descriptionPath() {
        String descname = name.substring(0, 8);
        return DIR_ROOT + parentCourse + "/" + descname + "/" + DESC_FILE;
    }

    public void clearParentCourse() {
        parentCourse = null;
    }

    public String getParentCourse() {
        return parentCourse;
    }

    public void setParentCourse(String course) {
        this.parentCourse = course;
    }

    public List<Lesson> getLessons() {
        return lessons;
    }

    public void addLesson(Lesson lesson) {
        lessons.add(lesson);
        lesson.setParentContentModule(name);
        lesson.setParentCourse(parentCourse);
    }

    public void removeLesson(Lesson lesson) {
        lessons.remove(lesson);
        lesson.clearParentContentModule();
        lesson.clearParentCourse();
    }

    public void setParents() {
        for (Lesson lesson : lessons) {
            lesson.setParentContentModule(name);
            lesson.setParentCourse(parentCourse);
        }
    }
}
