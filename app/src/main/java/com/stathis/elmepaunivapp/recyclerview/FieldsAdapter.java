package com.stathis.elmepaunivapp.recyclerview;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.stathis.elmepaunivapp.R;
import com.stathis.elmepaunivapp.models.DeptFieldsOfStudy;
import com.stathis.elmepaunivapp.models.ProfessorModel;

import java.util.List;

public class FieldsAdapter extends RecyclerView.Adapter<FieldsViewHolder> {

    private List<DeptFieldsOfStudy> fieldsOfStudies;

    //constructor of what I want to show to the user
    public FieldsAdapter(List<DeptFieldsOfStudy> fields) {
        fieldsOfStudies = fields;
    }

    @NonNull
    @Override
    public FieldsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.field_item_row, parent, false);
        return new FieldsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FieldsViewHolder holder, int position) {
        //we want to show the data to the users
        holder.present(fieldsOfStudies.get(position));
    }

    @Override
    public int getItemCount() {
        return fieldsOfStudies.size(); //how many items are in the list
    }
}
