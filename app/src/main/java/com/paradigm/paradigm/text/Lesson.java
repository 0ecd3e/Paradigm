package com.paradigm.paradigm.text;

public class Lesson extends Content {
    public String lessonContent;

    public Lesson() {
        super();
    }

    public Lesson(String name) {
        super(name);
    }

    @Override
    public String storedName() {
        return null;
    }

    public String getLessonContent() {
        return lessonContent;
    }

    public void setLessonContent(String content) {
        this.lessonContent = content;
    }
}
