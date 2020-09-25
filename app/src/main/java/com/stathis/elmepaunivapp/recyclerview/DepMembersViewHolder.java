package com.stathis.elmepaunivapp.recyclerview;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.stathis.elmepaunivapp.R;
import com.stathis.elmepaunivapp.listeners.OnItemClickListener;
import com.stathis.elmepaunivapp.models.DepMembers;

public class DepMembersViewHolder extends RecyclerView.ViewHolder {

    private TextView name;
    private ImageView Img;
    private OnItemClickListener onItemClickListener;
    private Object data;

    public DepMembersViewHolder(@NonNull View itemView, OnItemClickListener listener) {
        super(itemView);
        name = itemView.findViewById(R.id.dep_name);
        Img = itemView.findViewById(R.id.dep_img);
        onItemClickListener = listener;

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickListener.onDepProfessorClick((DepMembers) data);
            }
        });

    }

    public void present(DepMembers data) {
        this.data = data;
        name.setText(data.getName());
        Img.setImageResource(data.getImg());
    }
}
