package com.paradigm.paradigm.ui.explore;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.paradigm.paradigm.MainActivity;
import com.paradigm.paradigm.R;
import com.paradigm.paradigm.profile.UserProgress;
import com.paradigm.paradigm.profile.progressEntries.SaveProgressInterface;
import com.paradigm.paradigm.text.ContentModule;

import java.util.List;

public class ExploreModuleRecyclerViewAdapter extends RecyclerView.Adapter<ExploreModuleRecyclerViewAdapter.ViewHolder> {

    private final List<ContentModule> modulesList;

    final SaveProgressInterface listener;
    ExploreFragment parentFragment;

    public ExploreModuleRecyclerViewAdapter(List<ContentModule> modules,
                                            SaveProgressInterface saveProgressInterface,
                                            ExploreFragment context) {
        modulesList = modules;
        listener = saveProgressInterface;
        parentFragment = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_explore_module, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        ContentModule contentModule = modulesList.get(position);
        holder.contentModule = contentModule;

        holder.mIdView.setText(contentModule.getName());
        holder.mContentView.setText(contentModule.getDescription());

        String imageName = contentModule.getName();
        imageName = imageName.substring(0, 8);
        imageName = imageName.replace(" ", "");
        imageName = imageName.replace("M", "m");
        int id = parentFragment.getResources().getIdentifier(imageName, "drawable", parentFragment.requireContext().getPackageName());
        holder.imageView.setImageResource(id);


        holder.cardView.setOnClickListener(v -> {
            UserProgress userProgress = MainActivity.getUserProfile().getUserProgress();
            userProgress.setCurrentModule(contentModule);
            userProgress.setCheckpointModule(contentModule);
            listener.saveProgress();
            Navigation.findNavController(v).navigate(R.id.action_nav_explore_to_moduleFragment);
        });
    }

    @Override
    public int getItemCount() {
        //return mValues.size();
        return modulesList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mIdView;
        public final TextView mContentView;
        //public DummyItem mItem;
        public final ImageView imageView;
        public ContentModule contentModule;
        public final CardView cardView;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mIdView = view.findViewById(R.id.cardExploreModuleName);
            mContentView = view.findViewById(R.id.cardExploreModuleDesc);
            imageView = view.findViewById(R.id.cardExploreModuleImage);
            cardView = view.findViewById(R.id.cardExploreModule);
        }
/*
        @NonNull
        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }

 */
    }
}
