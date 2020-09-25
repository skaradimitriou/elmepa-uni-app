package com.stathis.elmepaunivapp.recyclerview;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.stathis.elmepaunivapp.R;
import com.stathis.elmepaunivapp.listeners.NewsClickListener;
import com.stathis.elmepaunivapp.ui.announcements.model.Announcement;

import java.util.ArrayList;

public class LatestNewsAdapter extends RecyclerView.Adapter<LatestNewsViewHolder> {

    private ArrayList<Announcement> latestNews;
    private NewsClickListener listener;

    public LatestNewsAdapter(ArrayList<Announcement> latestNews, NewsClickListener listener) {
        this.latestNews = latestNews;
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
        holder.present(latestNews.get(position));
    }

    @Override
    public int getItemCount() {
        return latestNews.size();
    }
}
