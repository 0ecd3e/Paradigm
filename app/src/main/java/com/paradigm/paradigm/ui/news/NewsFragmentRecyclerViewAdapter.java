package com.paradigm.paradigm.ui.news;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.paradigm.paradigm.R;
import com.paradigm.paradigm.newsfeed.Article;
import com.paradigm.paradigm.newsfeed.Feed;

import java.io.InputStream;
import java.net.URL;
import java.util.List;

public class NewsFragmentRecyclerViewAdapter extends RecyclerView.Adapter<NewsFragmentRecyclerViewAdapter.ViewHolder> {

    private Feed theFeed;
    private List<Article> newsFeed;

    public NewsFragmentRecyclerViewAdapter(Feed feed) {
        newsFeed = feed.getArticles();
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_news_element, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        Article article = newsFeed.get(position);
        holder.article = article;
        holder.mIdView.setText(article.getTitle());
        holder.mContentView.setText(article.getDescription());

        String date = article.getDate();
        String year = date;
        String time = date;
        year = year.substring(24);
        date = date.substring(4, 9);
        time = time.substring(11, 16);
        String cleanedDate = time + " " + date + ", " + year;
        holder.date.setText(cleanedDate);
        holder.author.setText(article.getAuthor());
        holder.url.setText(article.getUrl());
    }

    @Override
    public int getItemCount() {
        return newsFeed.size();
    }

    public void refreshFeed() {
        System.out.println("REFRESH\n");
    }

    public Bitmap loadImageFromURL(String url) {
        try {
            InputStream inputStream = new URL(url).openStream();
            return BitmapFactory.decodeStream(inputStream);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mIdView;
        public final TextView mContentView;
        public final TextView author;
        public final TextView date;
        public final TextView url;
        //public DummyItem mItem;
        //public ImageView imageView;
        public Article article;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mIdView = view.findViewById(R.id.newsArticleTitle);
            mContentView = view.findViewById(R.id.newsArticleText);
            //imageView = (ImageView) view.findViewById(R.id.newsArticleImage);
            author = view.findViewById(R.id.newsArticleAuthor);
            date = view.findViewById(R.id.newsArticleDate);
            url = view.findViewById(R.id.newsArticleUrl);
        }

        @NonNull
        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }
}
