package com.stathis.elmepaunivapp.ui.department.recyclerviews;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ListAdapter;

import com.stathis.elmepaunivapp.R;
import com.stathis.elmepaunivapp.abstraction.DiffItemCallbackClass;
import com.stathis.elmepaunivapp.listeners.SocialClickListener;
import com.stathis.elmepaunivapp.ui.department.model.SocialChannels;

public class SocialChannelAdapter extends ListAdapter<SocialChannels, SocialChannelsViewHolder> {

    private SocialClickListener listener;

    public SocialChannelAdapter(SocialClickListener listener) {
        super(new DiffItemCallbackClass<SocialChannels>());
        this.listener = listener;
    }

    @NonNull
    @Override
    public SocialChannelsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.social_item_row, parent, false);
        return new SocialChannelsViewHolder(view, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull SocialChannelsViewHolder holder, int position) {
        holder.present(getItem(position));
    }
}
