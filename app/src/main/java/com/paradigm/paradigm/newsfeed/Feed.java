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

@SuppressWarnings("CanBeFinal")
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
        for (Article article : list) {
            article.print();
        }
    }

    public String getDescContents(String description) {
        String contents = description.substring(description.indexOf("<p>"), description.indexOf("</p>"));
        contents = contents.replace("<p>", "");
        return contents;
    }

    public String getDescImage(String description) {
        String imageURL = description.substring(description.indexOf("<img src="), description.indexOf("' alt="));
        imageURL = imageURL.replace("<img src='", "");
        return imageURL;
    }

    public String getImageDescription(String description) {
        String imageDesc = description.substring(description.indexOf("title="), description.indexOf("' height="));
        imageDesc = imageDesc.replace("title='", "");
        return imageDesc;
    }

    public void setFeed(final String address) throws IOException, FeedException {
        //Establishes connection to RSS and retrieves article info.
        URL newsUrl = new URL(address);

        SyndFeedInput syndFeedInput = new SyndFeedInput();
        SyndFeed syndFeed = syndFeedInput.build(new XmlReader(newsUrl));

        List<SyndEntry> entries = syndFeed.getEntries();

        for (SyndEntry entry : entries) {
            Article article = new Article();

            article.setTitle(((SyndEntry) entry).getTitle());
            article.setUrl(((SyndEntry) entry).getLink());
            article.setAuthor(((SyndEntry) entry).getAuthor());
            article.setDate(((SyndEntry) entry).getPublishedDate().toString());
            String description = ((SyndEntry) entry).getDescription().getValue();
            String descContents = getDescContents(description);
            String image = getDescImage(description);
            String imageDesc = getImageDescription(description);
            article.setDescription(descContents);
            article.setImageURL(image);
            article.setImageDescription(imageDesc);

            list.add(article);
        }
    }

    public List<Article> getArticles() {
        return list;
    }
}
