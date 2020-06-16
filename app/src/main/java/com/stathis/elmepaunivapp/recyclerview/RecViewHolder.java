package com.stathis.elmepaunivapp.recyclerview;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.stathis.elmepaunivapp.R;

public class RecViewHolder extends RecyclerView.ViewHolder {
    public RecViewHolder(@NonNull View itemView) {
        super(itemView);
    }

    public void present(String data){
        TextView txt = itemView.findViewById(R.id.professor_name);
//        TextView prof_email = itemView.findViewById(R.id.professor_email);
        txt.setText(data);

    }
}
