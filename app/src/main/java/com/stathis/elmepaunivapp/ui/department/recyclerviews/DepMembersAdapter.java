package com.stathis.elmepaunivapp.ui.department.recyclerviews;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ListAdapter;

import com.stathis.elmepaunivapp.R;
import com.stathis.elmepaunivapp.abstraction.DiffItemCallbackClass;
import com.stathis.elmepaunivapp.listeners.DepMembersClickListener;
import com.stathis.elmepaunivapp.models.DepMembers;

public class DepMembersAdapter extends ListAdapter<DepMembers,DepMembersViewHolder> {

    private DepMembersClickListener listener;

    public DepMembersAdapter(DepMembersClickListener listener) {
        super(new DiffItemCallbackClass<DepMembers>());
        this.listener = listener;
    }

    @NonNull
    @Override
    public DepMembersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.dep_members_item_row, parent, false);
        return new DepMembersViewHolder(view, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull DepMembersViewHolder holder, int position) {
        holder.present(getItem(position));
    }
}
