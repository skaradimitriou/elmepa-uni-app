package com.stathis.elmepaunivapp.ui.syllabus.recyclerview;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ListAdapter;

import com.stathis.elmepaunivapp.R;
import com.stathis.elmepaunivapp.abstraction.DiffItemCallbackClass;
import com.stathis.elmepaunivapp.listeners.activity_listeners.SyllabusActivityListener;
import com.stathis.elmepaunivapp.ui.syllabus.model.Semester;

public class SemesterAdapter extends ListAdapter<Semester, SemesterViewHolder> {

    private SyllabusActivityListener listener;

    public SemesterAdapter(SyllabusActivityListener listener) {
        super(new DiffItemCallbackClass<Semester>());
        this.listener = listener;
    }

    @NonNull
    @Override
    public SemesterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SemesterViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.holder_semester_item_row, parent, false),listener);
    }

    @Override
    public void onBindViewHolder(@NonNull SemesterViewHolder holder, int position) {
        holder.present(getItem(position));
    }
}
