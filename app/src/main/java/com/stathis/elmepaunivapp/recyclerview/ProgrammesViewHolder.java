package com.stathis.elmepaunivapp.recyclerview;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.stathis.elmepaunivapp.R;
import com.stathis.elmepaunivapp.models.Programmes;

public class ProgrammesViewHolder extends RecyclerView.ViewHolder {

    private TextView name;
    private TextView description;

    public ProgrammesViewHolder(@NonNull View itemView) {
        super(itemView);
        name = itemView.findViewById(R.id.programmes_holder_txt);
        description = itemView.findViewById(R.id.programmes_description);
    }

    public void present(Programmes data) {
        name.setText(data.getName());
        description.setText(data.getDescription());
    }


}
