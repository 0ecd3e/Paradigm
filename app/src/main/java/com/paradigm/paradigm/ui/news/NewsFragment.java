package com.paradigm.paradigm.ui.news;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.paradigm.paradigm.MainActivity;
import com.paradigm.paradigm.R;
import com.paradigm.paradigm.newsfeed.Feed;
import com.rometools.rome.io.FeedException;

import java.io.IOException;

/**
 * A fragment representing a list of Items.
 */
public class NewsFragment extends Fragment {

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;
    SwipeRefreshLayout swipeRefreshLayout;
    View view;
    RecyclerView recyclerView;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public NewsFragment() {
    }

    // TODO: Customize parameter initialization
    public static NewsFragment newInstance(int columnCount) {
        NewsFragment fragment = new NewsFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_news, container, false);
        view = root.findViewById(R.id.newsFeedList);

        swipeRefreshLayout = root.findViewById(R.id.newsFeedSwipe);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new NewsFeedTask().execute(((MainActivity) requireActivity()).getFeedURL());
            }
        });

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            recyclerView = (RecyclerView) view;
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }
            //DummyContent.ITEMS
            //recyclerView.setAdapter(new NewsFragmentRecyclerViewAdapter();
            new NewsFeedTask().execute(((MainActivity) requireActivity()).getFeedURL());
        }
        return root;


    }

    private void displayExceptionToast() {
        Toast.makeText(requireContext(), "Check your feed URL in the settings.", Toast.LENGTH_SHORT).show();
    }

    public class NewsFeedTask extends AsyncTask<String, Integer, Feed> {
        boolean isSuccessful = false;

        @Override
        protected void onPreExecute() {
            swipeRefreshLayout.setRefreshing(true);
        }

        @Override
        protected Feed doInBackground(String... urls) {
            Feed theFeed = new Feed();
            try {
                theFeed.setFeed(urls[0]);
                isSuccessful = true;
            } catch (IOException | FeedException e) {
                return theFeed;
            }
            return theFeed;
        }

        @Override
        protected void onPostExecute(Feed feed) {
            swipeRefreshLayout.setRefreshing(false);
            recyclerView.setAdapter(new NewsFragmentRecyclerViewAdapter(feed));

            if (!isSuccessful) {
                displayExceptionToast();
            }
        }
    }
}
