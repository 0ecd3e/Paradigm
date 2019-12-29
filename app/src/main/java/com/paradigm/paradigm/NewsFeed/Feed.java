package com.paradigm.paradigm.NewsFeed;

import java.io.*;
import java.net.*;
import java.util.*;

public class Feed {

    private Vector<Article> list=new Vector<>();

    private static String getTitle(final String line){
        //Formats article title from RSS.
        int start = line.indexOf("<title>");
        String temp = line.substring(start);
        temp = temp.replace("<title>", "");
        int end = temp.indexOf("</title>");
        return temp.substring(0, end);
    }

    private static String getLink(final String line){
        int start=line.indexOf("<link>");
        String temp = line.substring(start);
        int end = temp.indexOf("</link>");
        return temp.substring(0,end);
    }

    private static String getTimeStamp(final String line){
        //Formats time stamp from RSS.
        int start = line.indexOf("<pubDate>");
        String timeStamp = line.substring(start);
        timeStamp = timeStamp.replace("<pubDate>", "");
        int end = timeStamp.indexOf("</pubDate>");
        return timeStamp.substring(0, end);
    }

    public void printList(){
        for (int i=0; i<list.size(); i++){
            list.elementAt(i).print();
        }
    }

    public void setFeed(final String address){
        //Establishes connection to RSS and retrieves article info.
        try {
            URL newsUrl = new URL(address);
            BufferedReader in = new BufferedReader(new InputStreamReader(newsUrl.openStream()));
            StringBuilder source= new StringBuilder();
            String line;
            Article temp=new Article();
            while ((line=in.readLine())!=null){
                if(line.contains("<title>")) {
                    temp.setTitle(getTitle(line));
                }
                if(line.contains("<pubDate>")){
                    temp.setTime(getTimeStamp(line));
                }
                if(line.contains("<link>")){
                    temp.setUrl(getLink(line));
                }
                list.add(temp);
            }
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args){
        String url="https://www.cbc.ca/cmlink/rss-canada";
        Feed f=new Feed();
        f.setFeed(url);
        f.printList();
    }
}
