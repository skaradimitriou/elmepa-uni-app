package com.stathis.elmepaunivapp.recyclerview;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.stathis.elmepaunivapp.R;
import com.stathis.elmepaunivapp.models.DeptFieldsOfStudy;
import com.stathis.elmepaunivapp.models.ProfessorModel;

public class FieldsViewHolder extends RecyclerView.ViewHolder {

    private TextView name;
    private ImageView imageView;

    public FieldsViewHolder(@NonNull View itemView) {
        super(itemView);
        name = itemView.findViewById(R.id.fields_holder_txt);
        imageView = itemView.findViewById(R.id.fields_img);
    }

    public void present(DeptFieldsOfStudy data) {
        name.setText(data.getName());
        imageView.setImageResource(data.getImageResource());
    }

}
