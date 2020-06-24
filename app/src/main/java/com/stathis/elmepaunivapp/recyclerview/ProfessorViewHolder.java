package com.stathis.elmepaunivapp.recyclerview;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.stathis.elmepaunivapp.R;
import com.stathis.elmepaunivapp.models.ProfessorModel;

public class ProfessorViewHolder extends RecyclerView.ViewHolder {

    private ItemClickListener ItemClickListener;
    private TextView fullName;
    private TextView email;
    private Object data;

    public ProfessorViewHolder(@NonNull View itemView, final ItemClickListener itemClickListener) {
        super(itemView);
        fullName = itemView.findViewById(R.id.professor_name);
        email = itemView.findViewById(R.id.professor_email);

        ItemClickListener = itemClickListener;
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ItemClickListener.onProfessorClick((ProfessorModel) data);
            }
        });
    }

    public void present(ProfessorModel data) {
        this.data = data;
        fullName.setText(data.getFullName());
        email.setText(data.getEmail());
    }
}
