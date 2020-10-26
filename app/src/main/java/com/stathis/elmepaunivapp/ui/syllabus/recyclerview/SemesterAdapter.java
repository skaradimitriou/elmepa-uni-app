package com.stathis.elmepaunivapp.ui.syllabus.recyclerview;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import com.stathis.elmepaunivapp.R;
import com.stathis.elmepaunivapp.abstraction.DiffItemCallbackClass;
import com.stathis.elmepaunivapp.ui.research.recycler.ResearchViewHolder;
import com.stathis.elmepaunivapp.ui.syllabus.model.Semester;

public class SemesterAdapter extends ListAdapter<Semester, SemesterViewHolder> {

    public SemesterAdapter() {
        super(new DiffItemCallbackClass<Semester>());
    }

    @NonNull
    @Override
    public SemesterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SemesterViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.holder_semester_item_row, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull SemesterViewHolder holder, int position) {
        holder.present(getItem(position));
    }
}
