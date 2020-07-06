package com.stathis.elmepaunivapp.recyclerview;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.stathis.elmepaunivapp.R;
import com.stathis.elmepaunivapp.models.Message;

import de.hdodenhof.circleimageview.CircleImageView;

public class ChatBotViewHolder extends RecyclerView.ViewHolder {

    private TextView question, answer;
    private CircleImageView user_img, bot_img;
    private ChatBotListener chatBotListener;
    private Object data;

    public ChatBotViewHolder(@NonNull View itemView, final ChatBotListener chatBotListener) {
        super(itemView);
        user_img = itemView.findViewById(R.id.user_img);
        bot_img = itemView.findViewById(R.id.bot_img);
        question = itemView.findViewById(R.id.user_msg_txt);
        answer = itemView.findViewById(R.id.bot_reply_txt);
        this.chatBotListener = chatBotListener;
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chatBotListener.onChatReply((Message) data);
            }
        });
    }

    public void present(Message data) {
        this.data = data;
        user_img.setImageResource(R.drawable.dimotikalis);
        question.setText(data.getQuestion());
        answer.setText(data.getAnswer());
    }
}
