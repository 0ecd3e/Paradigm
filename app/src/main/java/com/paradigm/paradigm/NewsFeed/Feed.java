package com.paradigm.paradigm.NewsFeed;

import java.io.*;
import java.net.*;
import java.util.*;
import java.sql.Timestamp;

public class Feed {

    private static String getTitle(final String curLine){
        //Formats article title from RSS.
        int start = curLine.indexOf("<title>");
        String temp = curLine.substring(start);
        temp = temp.replace("<title>", "");
        int end = temp.indexOf("</title>");
        temp = temp.substring(0, end);
        return temp;
    }

    private static String getTimeStamp(final String line){
        //Formats time stamp from RSS.
        int start = line.indexOf("<pubDate>");
        String timeStamp = line.substring(start);
        timeStamp = timeStamp.replace("<pubDate>", "");
        int end = timeStamp.indexOf("</pubDate>");
        timeStamp = timeStamp.substring(0, end);
        return timeStamp;
    }

    public static String readRSS(final String address){
        //Establishes connection to RSS and retrieves article infos.
        try {
            URL newsUrl = new URL(address);
            BufferedReader in = new BufferedReader(new InputStreamReader(newsUrl.openStream()));
            StringBuilder source= new StringBuilder();
            String line;

            while ((line=in.readLine())!=null){
                if(line.contains("<title>")) {
                    source.append(getTitle(line));
                }
                if(line.contains("<pubDate>")){
                    source.append("  ").append(getTimeStamp(line)).append("\n");
                }
            }
            in.close();
            return source.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static void main(String[] args){
        String url="https://www.cbc.ca/cmlink/rss-canada";
        String finalUrl=readRSS(url);
        System.out.print(finalUrl);
    }
}
