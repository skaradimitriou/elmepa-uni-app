package com.stathis.elmepaunivapp.recyclerviews;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.stathis.elmepaunivapp.R;
import com.stathis.elmepaunivapp.listeners.LessonClickListener;
import com.stathis.elmepaunivapp.models.Lesson;

import java.util.List;

public class LessonsAdapter extends RecyclerView.Adapter<LessonsViewHolder> {

    private List<Lesson> lessonList;
    private LessonClickListener listener;

    public LessonsAdapter(List<Lesson> lessonList, LessonClickListener listener) {
        this.lessonList = lessonList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public LessonsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lesson_item_row, parent, false);
        return new LessonsViewHolder(view,listener);
    }

    @Override
    public void onBindViewHolder(@NonNull LessonsViewHolder holder, int position) {
        holder.present(lessonList.get(position));
    }

    @Override
    public int getItemCount() {
        return lessonList.size();
    }

}
