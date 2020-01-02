package com.paradigm.paradigm.newsfeed;

public class Article {
    private String title;
    private String date;
    private String url;
    private String author;
    private String description;
    private String imageURL;
    private String imageDescription;

    public final String getTitle() {
        return title;
    }

    public void setTitle(final String name) {
        title = name;
    }

    public final String getDate() {
        return date;
    }

    public void setDate(final String timeStamp) {
        date = timeStamp;
    }

    public final String getUrl() {
        return url;
    }

    public void setUrl(final String link) {
        url = link;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getImageDescription() {
        return imageDescription;
    }

    public void setImageDescription(String imageDescription) {
        this.imageDescription = imageDescription;
    }

    public void print() {
        System.out.println(title);
        System.out.println(date);
        System.out.println(url);
        System.out.println(author);
        System.out.println(description);
        System.out.println(imageURL);
        System.out.println(imageDescription);
        System.out.println();
    }
}
