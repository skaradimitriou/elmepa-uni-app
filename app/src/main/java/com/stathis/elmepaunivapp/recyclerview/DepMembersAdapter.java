package com.stathis.elmepaunivapp.recyclerview;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.stathis.elmepaunivapp.R;
import com.stathis.elmepaunivapp.listeners.OnItemClickListener;
import com.stathis.elmepaunivapp.models.DepMembers;

import java.util.List;

public class DepMembersAdapter extends RecyclerView.Adapter<DepMembersViewHolder> {

    private List<DepMembers> depMembers;
    private OnItemClickListener onItemClickListener;

    public DepMembersAdapter(List<com.stathis.elmepaunivapp.models.DepMembers> depMembers, OnItemClickListener listener) {
        this.depMembers = depMembers;
        onItemClickListener = listener;
    }

    @NonNull
    @Override
    public DepMembersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.dep_members_item_row, parent, false);
        return new DepMembersViewHolder(view,onItemClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull DepMembersViewHolder holder, int position) {
        holder.present(depMembers.get(position));
    }

    @Override
    public int getItemCount() {
        return depMembers.size();
    }
}
