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

import com.paradigm.paradigm.R;
import com.paradigm.paradigm.dummy.DummyContent.DummyItem;
import com.paradigm.paradigm.profile.UserProgress;
import com.paradigm.paradigm.text.ContentModule;
import com.paradigm.paradigm.text.Lesson;

import java.util.List;


/**
 * {@link RecyclerView.Adapter} that can display a {@link DummyItem}.
 * TODO: Replace the implementation with code for your data type.
 */
public class ModuleFragmentRecyclerViewAdapter extends RecyclerView.Adapter<ModuleFragmentRecyclerViewAdapter.ViewHolder> {

    //private final List<DummyItem> mValues;
    private List<Lesson> lessonList;

    //public ModuleFragmentRecyclerViewAdapter(List<Lesson> items) {
    //mValues = items;
    //}

    public ModuleFragmentRecyclerViewAdapter(ContentModule module) {
        lessonList = module.getLessons();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_module_element, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        /*
        holder.mItem = mValues.get(position);
        String title = mValues.get(position).id + "Lesson Title";
        holder.mIdView.setText(title);
        String desc = mValues.get(position).content + "Lorem ipsum dolor sit amet, consectetur adipiscing elit.\n" +
                "        Nullam nisi velit, venenatis eget finibus sit amet, hendrerit commodo risus.";
        holder.mContentView.setText(desc);
        holder.imageView.setImageResource(R.drawable.smptebars);

         */

        Lesson lesson = lessonList.get(position);
        holder.lesson = lesson;
        holder.mIdView.setText(lesson.getName());
        holder.mContentView.setText(lesson.getDescription());
        holder.imageView.setImageResource(R.drawable.smptebars);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserProgress.setCurrentLesson(lesson);
                Navigation.findNavController(v).navigate(R.id.action_moduleFragment_to_lessonFragment);
            }
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
