package com.stathis.elmepaunivapp.ui.chatbot.recyclerview;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.stathis.elmepaunivapp.R;
import com.stathis.elmepaunivapp.abstraction.DiffItemCallbackClass;
import com.stathis.elmepaunivapp.listeners.ItemClickListener;
import com.stathis.elmepaunivapp.ui.chatbot.model.Answer;
import com.stathis.elmepaunivapp.ui.chatbot.model.ChatbotHeader;
import com.stathis.elmepaunivapp.ui.chatbot.model.Question;

public class ChatBotAdapter extends ListAdapter<Object, RecyclerView.ViewHolder> {

    private ItemClickListener itemClickListener;

    public ChatBotAdapter(@NonNull ItemClickListener itemClickListener) {
        super(new DiffItemCallbackClass<Object>());
        this.itemClickListener = itemClickListener;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(viewType, parent, false);
        if (viewType == R.layout.holder_question_item) {
            return new MessageViewHolder(view);
        } else if (viewType == R.layout.holder_answer_item){
            return new AnswerViewHolder(view, itemClickListener);
        } else if (viewType == R.layout.holder_chatbot_header){
            return new ChatbotHeaderViewHolder(view);
        } else {
            return null;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (getItem(position) instanceof Question) {
            ((MessageViewHolder) holder).present((Question) getItem(position));
        } else if (getItem(position) instanceof Answer) {
            ((AnswerViewHolder) holder).present((Answer) getItem(position));
        } else if (getItem(position) instanceof ChatbotHeader) {
            ((ChatbotHeaderViewHolder) holder).present((ChatbotHeader) getItem(position));
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (getItem(position) instanceof Question) {
            return R.layout.holder_question_item;
        } else if (getItem(position) instanceof Answer) {
            return R.layout.holder_answer_item;
        } else if (getItem(position) instanceof ChatbotHeader) {
            return R.layout.holder_chatbot_header;
        } else {
            return 3;
        }
    }
}
