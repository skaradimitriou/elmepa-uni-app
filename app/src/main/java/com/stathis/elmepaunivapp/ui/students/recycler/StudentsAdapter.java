package com.stathis.elmepaunivapp.ui.students.recycler;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ListAdapter;

import com.stathis.elmepaunivapp.R;
import com.stathis.elmepaunivapp.abstraction.DiffItemCallbackClass;
import com.stathis.elmepaunivapp.ui.research.recycler.ResearchViewHolder;
import com.stathis.elmepaunivapp.ui.students.model.Schedule;
import com.stathis.elmepaunivapp.ui.students.model.StudentItem;

public class StudentsAdapter extends ListAdapter<Object,StudentsViewHolder> {

    public StudentsAdapter() {
        super(new DiffItemCallbackClass<Object>());
    }

    @NonNull
    @Override
    public StudentsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new StudentsViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(viewType, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull StudentsViewHolder holder, int position) {
        holder.present(getItem(position));
    }

    @Override
    public int getItemViewType(int position) {
        if(getItem(position) instanceof StudentItem){
            return R.layout.holder_research_item_row;
        } else {
            return R.layout.holder_schedule_item_row;
        }
    }
}
