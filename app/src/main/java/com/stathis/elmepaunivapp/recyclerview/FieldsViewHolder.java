package com.stathis.elmepaunivapp.recyclerview;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.stathis.elmepaunivapp.R;
import com.stathis.elmepaunivapp.models.DeptFieldsOfStudy;
import com.stathis.elmepaunivapp.models.ProfessorModel;

public class FieldsViewHolder extends RecyclerView.ViewHolder {

    private TextView name;

    public FieldsViewHolder(@NonNull View itemView) {
        super(itemView);
        name = itemView.findViewById(R.id.fields_holder_txt);
    }

    public void present(DeptFieldsOfStudy data) {
        name.setText(data.getName());
    }

}
