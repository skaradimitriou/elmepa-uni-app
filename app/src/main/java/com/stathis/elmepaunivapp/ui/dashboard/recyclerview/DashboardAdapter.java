package com.stathis.elmepaunivapp.ui.dashboard.recyclerview;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import com.stathis.elmepaunivapp.R;
import com.stathis.elmepaunivapp.abstraction.DiffItemCallbackClass;
import com.stathis.elmepaunivapp.listeners.DashboardOptionListener;
import com.stathis.elmepaunivapp.listeners.DepMembersClickListener;
import com.stathis.elmepaunivapp.ui.dashboard.model.DashboardOption;
import com.stathis.elmepaunivapp.ui.department.recyclerviews.DepMembersViewHolder;

public class DashboardAdapter extends ListAdapter<DashboardOption,DashboardViewHolder> {

    private DashboardOptionListener listener;

    public DashboardAdapter(DashboardOptionListener listener) {
        super(new DiffItemCallbackClass<DashboardOption>());
        this.listener = listener;
    }

    @NonNull
    @Override
    public DashboardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.holder_dashboard_option, parent, false);
        return new DashboardViewHolder(view, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull DashboardViewHolder holder, int position) {
        holder.present(getItem(position));
    }
}
