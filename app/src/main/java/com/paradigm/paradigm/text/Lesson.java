package com.paradigm.paradigm.text;

public class Lesson extends Content {
    private String lessonContent;
    private String parentContentModule;
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
        return null;
    }

    public String getLessonContent() {
        return lessonContent;
    }

    public void setLessonContent(String content) {
        this.lessonContent = content;
    }

    public String lessonContentPath() {
        return "courses/" + parentCourse + "/" + parentContentModule + "/" + name + "/" + name + ".txt";
    }
}
