package com.stathis.elmepaunivapp.ui.announcements.recyclerviews;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ListAdapter;

import com.stathis.elmepaunivapp.R;
import com.stathis.elmepaunivapp.abstraction.DiffItemCallbackClass;
import com.stathis.elmepaunivapp.listeners.NewsClickListener;
import com.stathis.elmepaunivapp.ui.announcements.model.Announcement;

public class LatestNewsAdapter extends ListAdapter<Announcement,LatestNewsViewHolder> {

    private final int ANNOUNCEMENT = 1;
    private final int EMPTY = 2;
    private NewsClickListener listener;

    public LatestNewsAdapter(NewsClickListener listener) {
        super(new DiffItemCallbackClass<Announcement>());
        this.listener = listener;
    }


    @NonNull
    @Override
    public LatestNewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.announcement_item_row, parent, false);
        return new LatestNewsViewHolder(view,listener);
    }

    @Override
    public void onBindViewHolder(@NonNull LatestNewsViewHolder holder, int position) {
        holder.present(getItem(position));
    }
}
