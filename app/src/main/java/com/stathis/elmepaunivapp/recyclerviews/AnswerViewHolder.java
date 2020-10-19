package com.stathis.elmepaunivapp.recyclerviews;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.stathis.elmepaunivapp.R;
import com.stathis.elmepaunivapp.abstraction.AbstractViewHolder;
import com.stathis.elmepaunivapp.listeners.ItemClickListener;
import com.stathis.elmepaunivapp.ui.chatbot.model.Answer;
import com.stathis.elmepaunivapp.ui.chatbot.model.Question;

public class AnswerViewHolder extends AbstractViewHolder {

    private TextView answer;
    private Object data;
    private ItemClickListener itemClickListener;

    public AnswerViewHolder(@NonNull View itemView, ItemClickListener listener) {
        super(itemView);
        answer = itemView.findViewById(R.id.bot_reply_txt);
    }

    @Override
    public void present(Object data) {
        setData(data);
        if (data instanceof Answer){
            answer.setText(((Answer) data).getText());
        }
    }
}
