package com.stathis.elmepaunivapp.ui.students.recycler;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.stathis.elmepaunivapp.R;
import com.stathis.elmepaunivapp.listeners.UsefulLinkClickListener;
import com.stathis.elmepaunivapp.ui.students.model.UsefulLinks;

public class UsefulLinksViewHolder extends RecyclerView.ViewHolder {

    private UsefulLinkClickListener ItemClickListener;
    private TextView name;
    private ImageView imageView;
    private Object data;

    public UsefulLinksViewHolder(@NonNull View itemView, final UsefulLinkClickListener itemClickListener) {
        super(itemView);

        imageView = itemView.findViewById(R.id.links_img);
        name = itemView.findViewById(R.id.links_holder_txt);

        ItemClickListener = itemClickListener;
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ItemClickListener.onUsefulLinksClick((UsefulLinks) data);
            }
        });
    }

    public void present(UsefulLinks data) {
        this.data = data;
        name.setText(data.getName());
        imageView.setImageResource(data.getImageResource());
    }
}
