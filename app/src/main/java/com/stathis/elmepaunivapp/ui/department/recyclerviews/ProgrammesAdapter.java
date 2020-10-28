package com.stathis.elmepaunivapp.ui.department.recyclerviews;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ListAdapter;

import com.stathis.elmepaunivapp.R;
import com.stathis.elmepaunivapp.abstraction.DiffItemCallbackClass;
import com.stathis.elmepaunivapp.listeners.ProgrammesClickListener;
import com.stathis.elmepaunivapp.ui.department.model.Programmes;

public class ProgrammesAdapter extends ListAdapter<Programmes, ProgrammesViewHolder> {

    //    private List<Programmes> programmes;
    private ProgrammesClickListener listener;

    public ProgrammesAdapter(ProgrammesClickListener listener) {
        super(new DiffItemCallbackClass<Programmes>());
        this.listener = listener;
    }

    @NonNull
    @Override
    public ProgrammesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.programmes_item_row, parent, false);
        return new ProgrammesViewHolder(view, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull ProgrammesViewHolder holder, int position) {
        holder.present(getItem(position));
    }
}
