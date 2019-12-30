package com.paradigm.paradigm;


import com.paradigm.paradigm.newsfeed.Feed;

import org.junit.Test;

public class NewsFeedTest {
    @Test
    public void testFeed() {
        String url = "https://www.cbc.ca/cmlink/rss-canada";
        Feed f = new Feed();
        f.setFeed(url);
        f.printList();
    }
}
