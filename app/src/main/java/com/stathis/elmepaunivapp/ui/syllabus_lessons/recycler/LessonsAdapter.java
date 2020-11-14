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
import com.stathis.elmepaunivapp.ui.syllabus_lessons.model.LessonHeader;

public class LessonsAdapter extends ListAdapter<Object,LessonsViewHolder> {

    public LessonsAdapter() {
        super(new DiffItemCallbackClass<Object>());
    }

    @NonNull
    @Override
    public LessonsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(viewType, parent, false);
        return new LessonsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LessonsViewHolder holder, int position) {
        holder.present(getItem(position));
    }

    @Override
    public int getItemViewType(int position) {
        if(getItem(position) instanceof Lesson){
            return R.layout.lesson_item_row;
        } else if (getItem(position) instanceof LessonHeader) {
            return R.layout.holder_lessons_header_item;
        } else {
            return 1;
        }
    }
}
