package com.stathis.elmepaunivapp.abstraction;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.stathis.elmepaunivapp.listeners.ItemClickListener;
import com.stathis.elmepaunivapp.ui.chatbot.model.Answer;
import com.stathis.elmepaunivapp.ui.chatbot.model.Message;

public abstract class AbstractViewHolder<T extends Object> extends RecyclerView.ViewHolder {

    @Nullable
    protected ItemClickListener listener;
    T data;
    Answer dataTwo;

    public AbstractViewHolder(@NonNull View itemView) {
        super(itemView);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null && data != null) {
                    listener.onMessageClick(data);
                    listener.onAnswerClick(dataTwo);
                }
            }
        });
    }

    public void setListener(@NonNull ItemClickListener listener) {
        this.listener = listener;
    }

    public void setData(T data) {
        this.data = data;
    }

    public abstract void present(T data);
}
