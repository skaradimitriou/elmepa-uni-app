package com.stathis.elmepaunivapp.ui.department.recyclerviews;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.stathis.elmepaunivapp.R;
import com.stathis.elmepaunivapp.listeners.FieldsOfStudyListener;
import com.stathis.elmepaunivapp.models.DeptFieldsOfStudy;

public class FieldsViewHolder extends RecyclerView.ViewHolder {

    private FieldsOfStudyListener listener;
    private TextView name;
    private ImageView imageView;
    private Object data;

    public FieldsViewHolder(@NonNull View itemView, FieldsOfStudyListener itemClickListener) {
        super(itemView);
        name = itemView.findViewById(R.id.fields_holder_txt);
        imageView = itemView.findViewById(R.id.fields_img);

        listener = itemClickListener;
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onFieldOfStudyClick((DeptFieldsOfStudy) data);
            }
        });

    }

    public void present(DeptFieldsOfStudy data) {
        this.data = data;
        name.setText(data.getName());
        imageView.setImageResource(data.getImageResource());
    }

}
