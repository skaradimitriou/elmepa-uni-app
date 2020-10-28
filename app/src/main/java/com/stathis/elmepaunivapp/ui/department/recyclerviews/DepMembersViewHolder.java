package com.stathis.elmepaunivapp.ui.department.recyclerviews;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.stathis.elmepaunivapp.R;
import com.stathis.elmepaunivapp.listeners.DepMembersClickListener;
import com.stathis.elmepaunivapp.ui.department.model.DepMembers;

public class DepMembersViewHolder extends RecyclerView.ViewHolder {

    private TextView name;
    private ImageView Img;
    private DepMembersClickListener depMembersClickListener;
    private Object data;

    public DepMembersViewHolder(@NonNull View itemView, DepMembersClickListener listener) {
        super(itemView);
        name = itemView.findViewById(R.id.dep_name);
        Img = itemView.findViewById(R.id.dep_img);
        depMembersClickListener = listener;

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                depMembersClickListener.onDepProfessorClick((DepMembers) data);
            }
        });

    }

    public void present(DepMembers data) {
        this.data = data;
        name.setText(data.getName());
        Img.setImageResource(data.getImg());
    }
}
