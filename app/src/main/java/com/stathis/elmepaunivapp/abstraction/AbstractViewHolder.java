package com.stathis.elmepaunivapp.abstraction;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

abstract class AbstractViewHolder extends RecyclerView.ViewHolder {

    Object data;

    public AbstractViewHolder(@NonNull View itemView) {
        super(itemView);
    }

    public void present(Object data){
        this.data = data;
    }
}
