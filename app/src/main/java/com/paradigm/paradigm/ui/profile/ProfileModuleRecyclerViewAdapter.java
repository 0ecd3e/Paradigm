package com.paradigm.paradigm.ui.profile;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.paradigm.paradigm.R;
import com.paradigm.paradigm.dummy.DummyContent.DummyItem;
import com.paradigm.paradigm.profile.progressEntries.CourseProgress;
import com.paradigm.paradigm.profile.progressEntries.ModuleProgress;

import java.util.List;


/**
 * {@link RecyclerView.Adapter} that can display a {@link DummyItem}.
 * TODO: Replace the implementation with code for your data type.
 */
public class ProfileModuleRecyclerViewAdapter extends RecyclerView.Adapter<ProfileModuleRecyclerViewAdapter.ViewHolder> {

    //private final List<DummyItem> mValues;
    private List<ModuleProgress> modules;

    /*
    public ProfileModuleRecyclerViewAdapter(List<DummyItem> items) {
        mValues = items;
    }

     */

    public ProfileModuleRecyclerViewAdapter(CourseProgress courseProgress) {
        modules = courseProgress.getModules();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_profile_progresselement, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        /*
        holder.mItem = mValues.get(position);
        String title = mValues.get(position).id + "%";
        String desc = mValues.get(position).content + "Module Title";
        holder.mIdView.setText(desc);
        holder.mContentView.setText(title);
        holder.progressBar.setProgress(Integer.parseInt(mValues.get(position).id));

         */
        ModuleProgress moduleProgress = modules.get(position);
        moduleProgress.checkComplete();
        String completeness = moduleProgress.completePercentage() + "%";
        holder.mContentView.setText(completeness);
        String name = moduleProgress.getComponentName();
        String prettyName = name.replace("Java Basics,", "");
        holder.mIdView.setText(prettyName);
        holder.progressBar.setProgress(moduleProgress.completePercentage());
    }

    @Override
    public int getItemCount() {
        return modules.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mIdView;
        public final TextView mContentView;
        public final ProgressBar progressBar;
        //public DummyItem mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mIdView = (TextView) view.findViewById(R.id.profileProgressModuleTitle);
            mContentView = (TextView) view.findViewById(R.id.profileProgressModulePercent);
            progressBar = (ProgressBar) view.findViewById(R.id.profileProgressProgressBar);
        }

        @NonNull
        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }
}
