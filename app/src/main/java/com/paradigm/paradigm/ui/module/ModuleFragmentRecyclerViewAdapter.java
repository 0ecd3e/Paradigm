package com.paradigm.paradigm.ui.module;

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
import com.paradigm.paradigm.text.ContentModule;
import com.paradigm.paradigm.text.Lesson;

import java.util.List;

public class ModuleFragmentRecyclerViewAdapter extends RecyclerView.Adapter<ModuleFragmentRecyclerViewAdapter.ViewHolder> {

    private List<Lesson> lessonList;
    ModuleFragment parentFragment;

    public ModuleFragmentRecyclerViewAdapter(ContentModule module, ModuleFragment context) {
        lessonList = module.getLessons();
        parentFragment = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_module_element, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        Lesson lesson = lessonList.get(position);
        holder.lesson = lesson;
        holder.mIdView.setText(lesson.getName());
        holder.mContentView.setText(lesson.getDescription());

        String imageName = lesson.getParentContentModule();
        imageName = imageName.substring(0, 8);
        imageName = imageName.replace(" ", "");
        imageName = imageName.replace("M", "m");
        int id = parentFragment.getResources().getIdentifier(imageName, "drawable", parentFragment.requireContext().getPackageName());
        holder.imageView.setImageResource(id);

        holder.cardView.setOnClickListener(v -> {
            MainActivity.getUserProfile().getUserProgress().setCurrentLesson(lesson);
            Navigation.findNavController(v).navigate(R.id.action_moduleFragment_to_lessonFragment);
        });
    }

    @Override
    public int getItemCount() {
        return lessonList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mIdView;
        public final TextView mContentView;
        //public DummyItem mItem;
        public ImageView imageView;
        public CardView cardView;
        public Lesson lesson;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mIdView = view.findViewById(R.id.cardLessonName);
            mContentView = view.findViewById(R.id.cardLessonDescription);
            imageView = view.findViewById(R.id.cardLessonImage);
            cardView = view.findViewById(R.id.cardLesson);
        }

        @NonNull
        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }
}
