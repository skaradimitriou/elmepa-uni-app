package com.stathis.elmepaunivapp.recyclerview;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.stathis.elmepaunivapp.R;
import com.stathis.elmepaunivapp.models.SocialChannels;

import java.util.List;

public class SocialChannelAdapter extends RecyclerView.Adapter<SocialChannelsViewHolder> {

    private List<SocialChannels> socialChannels;

    public SocialChannelAdapter(List<SocialChannels> socialChannels) {
        this.socialChannels = socialChannels;
    }

    @NonNull
    @Override
    public SocialChannelsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.social_item_row, parent, false);
        return new SocialChannelsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SocialChannelsViewHolder holder, int position) {
            holder.present(socialChannels.get(position));
    }

    @Override
    public int getItemCount() {
        return socialChannels.size();
    }


}
