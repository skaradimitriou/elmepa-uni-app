package com.stathis.elmepaunivapp.recyclerview;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.stathis.elmepaunivapp.R;

import java.util.ArrayList;
import java.util.List;

public class RecAdapter extends RecyclerView.Adapter<RecViewHolder> {

    private List<String> arrayData = new ArrayList<String>();

    //constructor of what I want to show to the user
    public RecAdapter(List<String> array) {
        arrayData = array;
    }

    @NonNull
    @Override
    public RecViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //creating the view inside the rec adapter
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.holder_item_row, parent, false);
        return new RecViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecViewHolder holder, int position) {
        //we want to show the data to the users
        holder.present(arrayData.get(position));
    }

    @Override
    public int getItemCount() {
        return arrayData.size(); //how many items are in the list
    }
}
