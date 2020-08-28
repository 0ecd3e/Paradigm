package com.paradigm.paradigm.ui.explore;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.paradigm.paradigm.R;
import com.paradigm.paradigm.dummy.DummyContent.DummyItem;

import java.util.List;


/**
 * {@link RecyclerView.Adapter} that can display a {@link DummyItem}.
 * TODO: Replace the implementation with code for your data type.
 */
public class ExploreModuleRecyclerViewAdapter extends RecyclerView.Adapter<ExploreModuleRecyclerViewAdapter.ViewHolder> {

    private final List<DummyItem> mValues;

    public ExploreModuleRecyclerViewAdapter(List<DummyItem> items) {
        mValues = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_explore_module, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        String title = mValues.get(position).id + "Default title";
        holder.mIdView.setText(title);
        String desc = mValues.get(position).content + "Lorem ipsum dolor sit amet, consectetur adipiscing elit.\n" +
                "        Nullam nisi velit, venenatis eget finibus sit amet, hendrerit commodo risus.";
        holder.mContentView.setText(desc);
        holder.imageView.setImageResource(R.drawable.smptebars);
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mIdView;
        public final TextView mContentView;
        public DummyItem mItem;
        public ImageView imageView;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mIdView = (TextView) view.findViewById(R.id.cardExploreModuleName);
            mContentView = (TextView) view.findViewById(R.id.cardExploreModuleDesc);
            imageView = (ImageView) view.findViewById(R.id.cardExploreModuleImage);
        }

        @NonNull
        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }
}