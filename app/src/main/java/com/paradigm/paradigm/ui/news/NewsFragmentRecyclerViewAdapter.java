package com.paradigm.paradigm.ui.news;

import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.paradigm.paradigm.MainActivity;
import com.paradigm.paradigm.R;
import com.paradigm.paradigm.dummy.DummyContent.DummyItem;
import com.paradigm.paradigm.newsfeed.Article;
import com.paradigm.paradigm.newsfeed.Feed;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


/**
 * {@link RecyclerView.Adapter} that can display a {@link DummyItem}.
 * TODO: Replace the implementation with code for your data type.
 */
public class NewsFragmentRecyclerViewAdapter extends RecyclerView.Adapter<NewsFragmentRecyclerViewAdapter.ViewHolder> {

    private final List<DummyItem> mValues = new ArrayList<>();
    private List<Article> newsFeed;

    public NewsFragmentRecyclerViewAdapter(List<DummyItem> items) {
        //mValues = items;
    }

    public NewsFragmentRecyclerViewAdapter(Feed feed) {
        feed.setFeed(MainActivity.feedURL);
        newsFeed = feed.getArticles();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_news_element, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        /*
        holder.mItem = mValues.get(position);
        String title = mValues.get(position).id + "News Title";
        holder.mIdView.setText(title);
        String desc = mValues.get(position).content + "Lorem ipsum dolor sit amet, consectetur adipiscing elit.\n" +
                "        Nullam nisi velit, venenatis eget finibus sit amet, hendrerit commodo risus.";
        holder.mContentView.setText(desc);

         */

        holder.article = newsFeed.get(position);
        Drawable feedImage = loadImageFromURL(holder.article.getImageURL());
        holder.imageView.setImageDrawable(feedImage);
        holder.mIdView.setText(holder.article.getTitle());
        holder.mContentView.setText(holder.article.getDescription());

    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public Drawable loadImageFromURL(String url) {
        try {
            InputStream inputStream = (InputStream) new URL(url).getContent();
            return Drawable.createFromStream(inputStream, "src");
        } catch (Exception e) {
            return null;
        }
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mIdView;
        public final TextView mContentView;
        //public DummyItem mItem;
        public ImageView imageView;
        public Article article;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mIdView = (TextView) view.findViewById(R.id.newsArticleTitle);
            mContentView = (TextView) view.findViewById(R.id.newsArticleText);
            imageView = (ImageView) view.findViewById(R.id.newsArticleImage);
        }

        @NonNull
        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }
}
