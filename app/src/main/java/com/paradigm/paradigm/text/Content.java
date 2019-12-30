package com.paradigm.paradigm.text;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import java.io.Serializable;

@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = ContentModule.class),
        @JsonSubTypes.Type(value = Course.class),
        @JsonSubTypes.Type(value = Lesson.class),
})
public abstract class Content implements Serializable {
    public static final String DESC_FILE = "description.txt";
    public static final String DIR_ROOT = "courses/";
    public static final String Q_FILE = "questions.json";
    protected String name;
    protected String description;
//    protected boolean isComplete;

    public Content() {
        super();
    }

    public Content(String name) {
        this.name = name;
//        isComplete = false;
    }

    public abstract String descriptionPath();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String text) {
        this.description = text;
    }
//
//    public boolean isComplete() {
//        return isComplete;
//    }
//
//    public void setComplete(boolean status) {
//        this.isComplete = status;
//    }
}
