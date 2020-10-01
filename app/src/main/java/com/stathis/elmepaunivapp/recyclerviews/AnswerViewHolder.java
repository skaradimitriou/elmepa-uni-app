package com.stathis.elmepaunivapp.recyclerviews;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.stathis.elmepaunivapp.R;
import com.stathis.elmepaunivapp.abstraction.AbstractViewHolder;
import com.stathis.elmepaunivapp.listeners.ItemClickListener;
import com.stathis.elmepaunivapp.ui.chatbot.model.Answer;

public class AnswerViewHolder extends AbstractViewHolder {

    private TextView answer;
    private Object data;
    private ItemClickListener itemClickListener;

    public AnswerViewHolder(@NonNull View itemView, ItemClickListener listener) {
        super(itemView);
        answer = itemView.findViewById(R.id.bot_reply_txt);
        itemClickListener = listener;
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemClickListener.onAnswerClick((Answer) data);
            }
        });
    }

    @Override
    public void present(Object data) {
        if (data instanceof Answer){
            this.data = data;
            answer.setText(((Answer) data).getText());
        }
    }
}
