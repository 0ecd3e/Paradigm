package com.paradigm.paradigm;


import com.paradigm.paradigm.newsfeed.Feed;
import com.rometools.rome.io.FeedException;

import org.junit.Test;

import java.io.IOException;

public class NewsFeedTest {
    @Test
    public void testFeed() throws IOException, FeedException {
//        String url = "https://www.cbc.ca/cmlink/rss-canada";
        String url = "https://rss.cbc.ca/lineup/technology.xml";
        Feed f = new Feed();
        f.setFeed(url);
        f.printList();
    }
}
