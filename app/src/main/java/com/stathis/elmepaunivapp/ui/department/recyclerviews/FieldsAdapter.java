package com.stathis.elmepaunivapp.ui.department.recyclerviews;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ListAdapter;

import com.stathis.elmepaunivapp.R;
import com.stathis.elmepaunivapp.abstraction.DiffItemCallbackClass;
import com.stathis.elmepaunivapp.listeners.FieldsOfStudyListener;
import com.stathis.elmepaunivapp.models.DeptFieldsOfStudy;

public class FieldsAdapter extends ListAdapter<DeptFieldsOfStudy, FieldsViewHolder> {

    private FieldsOfStudyListener callback;

    public FieldsAdapter(FieldsOfStudyListener callback) {
        super(new DiffItemCallbackClass<DeptFieldsOfStudy>());
        this.callback = callback;
    }

    @NonNull
    @Override
    public FieldsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.field_item_row, parent, false);
        return new FieldsViewHolder(view, callback);
    }

    @Override
    public void onBindViewHolder(@NonNull FieldsViewHolder holder, int position) {
        //we want to show the data to the users
        holder.present(getItem(position));
    }
}
