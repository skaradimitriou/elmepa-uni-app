package com.stathis.elmepaunivapp.recyclerviews;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.stathis.elmepaunivapp.R;
import com.stathis.elmepaunivapp.abstraction.AbstractViewHolder;
import com.stathis.elmepaunivapp.listeners.ItemClickListener;
import com.stathis.elmepaunivapp.ui.chatbot.model.Answer;
import com.stathis.elmepaunivapp.ui.chatbot.model.Message;
import com.stathis.elmepaunivapp.ui.chatbot.model.Question;

public class MessageViewHolder extends AbstractViewHolder {

    private TextView question;
    private ItemClickListener itemClickListener;
    private Object data;

    public MessageViewHolder(@NonNull View itemView, ItemClickListener listener) {
        super(itemView);
        question = itemView.findViewById(R.id.user_msg_txt);
        itemClickListener = listener;
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemClickListener.onMessageClick((Question) data);
            }
        });
    }

    public void present(Question data){
        this.data = data;
        question.setText(data.getText());
    }

    @Override
    public void present(Object data) {
        if(data instanceof Question){
            this.data = data;
            question.setText(((Question) data).getText());
        }
    }
}
