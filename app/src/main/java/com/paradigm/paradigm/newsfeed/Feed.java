package com.paradigm.paradigm.newsfeed;

import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.FeedException;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class Feed {

    private ArrayList<Article> list = new ArrayList<>();

    private static String getTitle(final String line) {
        //Formats article title from RSS.
        int start = line.indexOf("<title>");
        String temp = line.substring(start);
        temp = temp.replace("<title>", "");
        int end = temp.indexOf("</title>");
        return temp.substring(0, end);
    }

    private static String getLink(final String line) {
        int start = line.indexOf("<link>");
        String temp = line.substring(start);
        int end = temp.indexOf("</link>");
        return temp.substring(0, end);
    }

    private static String getTimeStamp(final String line) {
        //Formats time stamp from RSS.
        int start = line.indexOf("<pubDate>");
        String timeStamp = line.substring(start);
        timeStamp = timeStamp.replace("<pubDate>", "");
        int end = timeStamp.indexOf("</pubDate>");
        return timeStamp.substring(0, end);
    }

    public void printList() {
//        for (int i=0; i<list.size(); i++){
//            list.elementAt(i).print();
//        }
        for (Article article : list) {
            article.print();
        }
    }

    //TODO: likely a problem with the loop, not getting the next article properly
    public void setFeed(final String address) {
        //Establishes connection to RSS and retrieves article info.
        try {
            URL newsUrl = new URL(address);
//            BufferedReader in = new BufferedReader(new InputStreamReader(newsUrl.openStream()));
//            String line;
//
//            Article temp = new Article();
//
//            while ((line = in.readLine()) != null) {
//                if (line.contains("<title>")) {
//                    temp.setTitle(getTitle(line));
//                }
//                if (line.contains("<pubDate>")) {
//                    temp.setTime(getTimeStamp(line));
//                }
//                if (line.contains("<link>")) {
//                    temp.setUrl(getLink(line));
//                }
//                list.add(temp);
//            }
//            in.close();

//            BufferedReader in = new BufferedReader(new InputStreamReader(newsUrl.openStream()));
//            String line;
//            while ((line = in.readLine()) != null) {
//                System.out.println(line);
//            }
//            in.close();

            SyndFeedInput syndFeedInput = new SyndFeedInput();
            SyndFeed syndFeed = syndFeedInput.build(new XmlReader(newsUrl));

            List entries = syndFeed.getEntries();

            for (Object entry : entries) {
                System.out.println("ENTRY START");
                SyndEntry syndEntry = (SyndEntry) entry;
                System.out.println(syndEntry.getTitle());
                System.out.println(syndEntry.getLink());
                System.out.println(syndEntry.getAuthor());
                System.out.println(syndEntry.getPublishedDate());
                System.out.println(syndEntry.getDescription().getValue());
                System.out.println("ENTRY END");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (FeedException e) {
            e.printStackTrace();
        }
    }
}
