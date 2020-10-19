package com.stathis.elmepaunivapp.ui.professors.recyclerview;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ListAdapter;

import com.stathis.elmepaunivapp.R;
import com.stathis.elmepaunivapp.abstraction.DiffItemCallbackClass;
import com.stathis.elmepaunivapp.listeners.ProfessorClickListener;
import com.stathis.elmepaunivapp.ui.professors.model.ProfessorModel;

public class ProfessorAdapter extends ListAdapter<ProfessorModel,ProfessorViewHolder> {

    private ProfessorClickListener listener;

    public ProfessorAdapter(ProfessorClickListener listener) {
        super(new DiffItemCallbackClass<ProfessorModel>());
        this.listener = listener;
    }

    @NonNull
    @Override
    public ProfessorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.professor_item_row, parent, false);
        return new ProfessorViewHolder(view, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull ProfessorViewHolder holder, int position) {
        holder.present(getItem(position));
    }
}
