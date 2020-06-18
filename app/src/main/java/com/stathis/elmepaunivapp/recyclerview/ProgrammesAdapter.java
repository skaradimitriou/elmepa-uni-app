package com.stathis.elmepaunivapp.recyclerview;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.stathis.elmepaunivapp.R;
import com.stathis.elmepaunivapp.models.Programmes;

import java.util.List;

public class ProgrammesAdapter extends RecyclerView.Adapter<ProgrammesViewHolder> {

    private List<Programmes> programmes;

    public ProgrammesAdapter(List<Programmes> programmes) {
        this.programmes = programmes;
    }

    @NonNull
    @Override
    public ProgrammesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.programmes_item_row, parent, false);
        return new ProgrammesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProgrammesViewHolder holder, int position) {
        holder.present(programmes.get(position));
    }

    @Override
    public int getItemCount() {
       return programmes.size();
    }
}
