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
    protected String name;
    protected boolean isComplete;

    public Content() {
        super();
    }

    public Content(String name) {
        this.name = name;
        isComplete = false;
    }

    public abstract String storedName();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isComplete() {
        return isComplete;
    }

    public void setComplete(boolean status) {
        this.isComplete = status;
    }
}
