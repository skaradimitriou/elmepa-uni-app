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
    private NewsClickListener newsClickListener;
    private Object data;

    public LatestNewsViewHolder(@NonNull View itemView, NewsClickListener listener) {
        super(itemView);
        name = itemView.findViewById(R.id.ann_txt);
        imageView = itemView.findViewById(R.id.ann_img);
        newsClickListener = listener;
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newsClickListener.onNewsClick((Announcement) data);
            }
        });
    }

    public void present(Announcement data) {
        this.data = data;
        name.setText(data.getName());
        Picasso.get().load(data.getImageResource()).into(imageView);
    }


}
