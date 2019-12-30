package com.paradigm.paradigm.text;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@JsonIdentityInfo(generator= ObjectIdGenerators.IntSequenceGenerator.class, property="@id")
public class Lesson extends Content {
    @JsonProperty("lessonContent")
    private String lessonContent;
    @JsonProperty("parentContentModule")
    private String parentContentModule;
    @JsonProperty("parentCourse")
    private String parentCourse;

    public Lesson() {
        super();
    }

    public Lesson(String name) {
        super(name);
    }

    public String getParentContentModule() {
        return parentContentModule;
    }

    public void setParentContentModule(String contentModule) {
        this.parentContentModule = contentModule;
    }

    public void clearParentContentModule() {
        this.parentContentModule = null;
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

    @Override
    public String descriptionPath() {
        return DIR_ROOT + parentCourse + "/" + parentContentModule + "/" + name + "/" + DESC_FILE;
    }

    public String getLessonContent() {
        return lessonContent;
    }

    public void setLessonContent(String content) {
        this.lessonContent = content;
    }

    public String lessonContentPath() {
        return DIR_ROOT + parentCourse + "/" + parentContentModule + "/" + name + "/" + name + ".txt";
    }
}
