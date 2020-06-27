package com.stathis.elmepaunivapp.recyclerview;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import com.stathis.elmepaunivapp.R;
import com.stathis.elmepaunivapp.models.Announcement;

public class LatestNewsViewHolder extends RecyclerView.ViewHolder {

    private TextView name;
    private ImageView imageView;

    public LatestNewsViewHolder(@NonNull View itemView) {
        super(itemView);
        name = itemView.findViewById(R.id.ann_txt);
        imageView = itemView.findViewById(R.id.ann_img);
    }

    public void present(Announcement data){
        name.setText(data.getName());
        Picasso.get().load(data.getImageResource()).into(imageView);
    }


}
