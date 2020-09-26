package com.stathis.elmepaunivapp.ui.professors.recyclerview;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.stathis.elmepaunivapp.R;
import com.stathis.elmepaunivapp.abstraction.DiffItemCallbackClass;
import com.stathis.elmepaunivapp.listeners.ProfessorClickListener;
import com.stathis.elmepaunivapp.ui.professors.model.ProfessorModel;

import java.util.ArrayList;
import java.util.List;

public class ProfessorAdapter extends ListAdapter<ProfessorModel,ProfessorViewHolder> {

    private List<ProfessorModel> arrayData = new ArrayList<>();
    private ProfessorClickListener listener;

    //constructor of what I want to show to the user
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
        //we want to show the data to the users
        holder.present(getItem(position));
    }
}
