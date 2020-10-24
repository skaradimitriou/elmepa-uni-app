package com.stathis.elmepaunivapp.ui.chatbot.recyclerview;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.stathis.elmepaunivapp.R;
import com.stathis.elmepaunivapp.listeners.ItemClickListener;
import com.stathis.elmepaunivapp.ui.chatbot.model.Question;

public class MessageViewHolder extends RecyclerView.ViewHolder {

    private TextView question;
    private ItemClickListener itemClickListener;
    private Object data;

    public MessageViewHolder(@NonNull View itemView) {
        super(itemView);
        question = itemView.findViewById(R.id.user_msg_txt);
    }

    public void present(Question data){
        this.data = data;
        question.setText(data.getText());
    }
}
