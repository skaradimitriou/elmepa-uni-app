package com.stathis.elmepaunivapp.recyclerview;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.stathis.elmepaunivapp.R;
import com.stathis.elmepaunivapp.models.ProfessorModel;

import java.util.ArrayList;
import java.util.List;

public class ProfessorAdapter extends RecyclerView.Adapter<ProfessorViewHolder> {

    private List<ProfessorModel> arrayData = new ArrayList<ProfessorModel>();
    private ItemClickListener listener;

    //constructor of what I want to show to the user
    public ProfessorAdapter(List<ProfessorModel> array, ItemClickListener listener) {
        arrayData = array;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ProfessorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //creating the view inside the rec adapter
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.professor_item_row, parent, false);
        return new ProfessorViewHolder(view, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull ProfessorViewHolder holder, int position) {
        //we want to show the data to the users
        holder.present(arrayData.get(position));
    }

    @Override
    public int getItemCount() {
        return arrayData.size(); //how many items are in the list
    }

    public void filterList(ArrayList<ProfessorModel> filteredList) {
        arrayData = filteredList;
        notifyDataSetChanged();
    }
}
