package com.paradigm.paradigm.newsfeed;

public class Article {
    private String title;
    private String time;
    private String url;


    public void setTitle(final String name){
        title=name;
    }

    public void setTime(final String timeStamp){
        time=timeStamp;
    }

    public void setUrl(final String link){
        url=link;
    }

    public final String getTitle(){ return  title; }

    public final String getTime(){ return time;}

    public final String getUrl(){ return url; }

    public void print(){
        System.out.print(title);
        System.out.print(time);
        System.out.print(url+"\n");
    }

    public static void main(String[] args){

    }


}
