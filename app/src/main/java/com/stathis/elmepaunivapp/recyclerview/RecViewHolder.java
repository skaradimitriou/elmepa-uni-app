package com.stathis.elmepaunivapp.recyclerview;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.card.MaterialCardView;
import com.stathis.elmepaunivapp.R;
import com.stathis.elmepaunivapp.models.ProfessorModel;

public class RecViewHolder extends RecyclerView.ViewHolder {

    private TextView fullName;
    private TextView email;

    public RecViewHolder(@NonNull View itemView) {
        super(itemView);
        fullName = itemView.findViewById(R.id.professor_name);
        email = itemView.findViewById(R.id.professor_email);
    }

    public void present(ProfessorModel data) {
        fullName.setText(data.getFullName());
        email.setText(data.getEmail());
    }
}
