package com.paradigm.paradigm.newsfeed;

public class Article {
    private String title;
    private String time;
    private String url;

    public final String getTitle() {
        return title;
    }

    public void setTitle(final String name) {
        title = name;
    }

    public final String getTime() {
        return time;
    }

    public void setTime(final String timeStamp) {
        time = timeStamp;
    }

    public final String getUrl() {
        return url;
    }

    public void setUrl(final String link) {
        url = link;
    }

    public void print() {
        System.out.print(title);
        System.out.print(time);
        System.out.print(url + "\n");
    }
}
