package com.stathis.elmepaunivapp.recyclerview;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.stathis.elmepaunivapp.R;
import com.stathis.elmepaunivapp.models.DeptFieldsOfStudy;
import com.stathis.elmepaunivapp.models.SocialChannels;

public class SocialChannelsViewHolder extends RecyclerView.ViewHolder {

    private ItemClickListener ItemClickListener;
    private TextView name;
    private ImageView imageView;
    private Object data;

    public SocialChannelsViewHolder(@NonNull final View itemView, ItemClickListener listener) {
        super(itemView);
        name = itemView.findViewById(R.id.social_txt);
        imageView = itemView.findViewById(R.id.social_circle_img);
        ItemClickListener = listener;
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ItemClickListener.onSocialItemClick((SocialChannels) data);
            }
        });
    }

    public void present(SocialChannels data) {
        this.data = data;
        name.setText(data.getName());
        imageView.setImageResource(data.getImg());
    }

}
