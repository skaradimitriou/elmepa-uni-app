package com.stathis.elmepaunivapp.ui.students.recycler;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ListAdapter;

import com.stathis.elmepaunivapp.R;
import com.stathis.elmepaunivapp.abstraction.DiffItemCallbackClass;
import com.stathis.elmepaunivapp.listeners.ScheduleClickListener;
import com.stathis.elmepaunivapp.listeners.UsefulLinkClickListener;
import com.stathis.elmepaunivapp.listeners.activity_listeners.StudentsActivityListener;
import com.stathis.elmepaunivapp.ui.students.model.StudentItem;

public class StudentsAdapter extends ListAdapter<Object,StudentsViewHolder> {

    UsefulLinkClickListener listener;
    ScheduleClickListener scheduleListener;

    public StudentsAdapter(UsefulLinkClickListener listener,ScheduleClickListener scheduleListener) {
        super(new DiffItemCallbackClass<Object>());
        this.listener = listener;
        this.scheduleListener = scheduleListener;
    }

    @NonNull
    @Override
    public StudentsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new StudentsViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(viewType, parent, false),listener,scheduleListener);
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
