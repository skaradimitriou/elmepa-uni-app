package com.stathis.elmepaunivapp.ui.syllabus_lessons.recycler;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ListAdapter;

import com.stathis.elmepaunivapp.R;
import com.stathis.elmepaunivapp.abstraction.DiffItemCallbackClass;
import com.stathis.elmepaunivapp.listeners.LessonClickListener;
import com.stathis.elmepaunivapp.ui.syllabus_lessons.model.Lesson;

public class LessonsAdapter extends ListAdapter<Lesson,LessonsViewHolder> {

    private LessonClickListener listener;

    public LessonsAdapter(LessonClickListener listener) {
        super(new DiffItemCallbackClass<Lesson>());
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
        holder.present(getItem(position));
    }

}
