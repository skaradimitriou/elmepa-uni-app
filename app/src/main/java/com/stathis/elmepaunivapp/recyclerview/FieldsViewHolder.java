package com.stathis.elmepaunivapp.recyclerview;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.stathis.elmepaunivapp.R;
import com.stathis.elmepaunivapp.listeners.ItemClickListener;
import com.stathis.elmepaunivapp.models.DeptFieldsOfStudy;

public class FieldsViewHolder extends RecyclerView.ViewHolder {

    private com.stathis.elmepaunivapp.listeners.ItemClickListener ItemClickListener;
    private TextView name;
    private ImageView imageView;
    private Object data;

    public FieldsViewHolder(@NonNull View itemView, ItemClickListener itemClickListener) {
        super(itemView);
        name = itemView.findViewById(R.id.fields_holder_txt);
        imageView = itemView.findViewById(R.id.fields_img);

        ItemClickListener = itemClickListener;
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ItemClickListener.onItemClick((DeptFieldsOfStudy) data);
            }
        });

    }

    public void present(DeptFieldsOfStudy data) {
        this.data = data;
        name.setText(data.getName());
        imageView.setImageResource(data.getImageResource());
    }

}
