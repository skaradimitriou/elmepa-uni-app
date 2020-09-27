package com.stathis.elmepaunivapp.abstraction;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import com.stathis.elmepaunivapp.listeners.AbstractClickListener;

public class AbstractAdapter extends ListAdapter<Object,AbstractViewHolder> {

    private AbstractClickListener listener;

    protected AbstractAdapter(AbstractClickListener listener) {
        super(new DiffItemCallbackClass<Object>());
        this.listener = listener;
    }

    @NonNull
    @Override
    public AbstractViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // TODO("Implement View Holder")
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull AbstractViewHolder holder, int position) {
        holder.present(getItem(position));
    }
}
