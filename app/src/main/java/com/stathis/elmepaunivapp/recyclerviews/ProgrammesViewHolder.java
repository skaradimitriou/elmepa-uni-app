package com.stathis.elmepaunivapp.recyclerviews;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.stathis.elmepaunivapp.R;
import com.stathis.elmepaunivapp.listeners.ItemClickListener;
import com.stathis.elmepaunivapp.models.Programmes;

public class ProgrammesViewHolder extends RecyclerView.ViewHolder {

    private TextView name;
    private TextView description;
    private ImageView imageView;
    private com.stathis.elmepaunivapp.listeners.ItemClickListener ItemClickListener;
    private Object data;

    public ProgrammesViewHolder(@NonNull View itemView, ItemClickListener itemClickListener) {
        super(itemView);
        name = itemView.findViewById(R.id.programmes_holder_txt);
        description = itemView.findViewById(R.id.programmes_description);
        imageView = itemView.findViewById(R.id.programmes_img);

        ItemClickListener = itemClickListener;
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ItemClickListener.onProgrammesClick((Programmes) data);
            }
        });
    }

    public void present(Programmes data) {
        this.data = data;
        name.setText(data.getName());
        description.setText(data.getDescription());
        imageView.setImageResource(data.getImageResource());
    }


}
