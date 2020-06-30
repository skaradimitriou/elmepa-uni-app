package com.stathis.elmepaunivapp.recyclerview;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.stathis.elmepaunivapp.R;
import com.stathis.elmepaunivapp.models.DepMembers;
import com.stathis.elmepaunivapp.models.DeptFieldsOfStudy;

public class DepMembersViewHolder extends RecyclerView.ViewHolder {

    private TextView name;
    private ImageView Img;
    private Object data;

    public DepMembersViewHolder(@NonNull View itemView) {
        super(itemView);
        name = itemView.findViewById(R.id.dep_name);
        Img = itemView.findViewById(R.id.dep_img);
    }

    public void present(DepMembers data) {
        this.data = data;
        name.setText(data.getName());
        Img.setImageResource(data.getImg());
    }
}
