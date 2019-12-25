package com.paradigm.paradigm.text;

public abstract class Content {
    protected String name;
    protected boolean isComplete;

    public Content(String name) {
        this.name = name;
        isComplete = false;
    }

    public abstract String getFileName();
}
