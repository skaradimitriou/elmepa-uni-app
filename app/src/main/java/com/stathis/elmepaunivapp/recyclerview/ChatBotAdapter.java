package com.stathis.elmepaunivapp.recyclerview;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.stathis.elmepaunivapp.R;
import com.stathis.elmepaunivapp.models.Message;

import java.util.ArrayList;
import java.util.List;

public class ChatBotAdapter extends RecyclerView.Adapter<ChatBotViewHolder> {

    private List<Message> messages = new ArrayList<>();
    private ChatBotListener chatBotListener;

    public ChatBotAdapter(List<Message> messages, ChatBotListener listener) {
        this.messages = messages;
        chatBotListener = listener;
    }

    @NonNull
    @Override
    public ChatBotViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.message_item_row, parent, false);
        return new ChatBotViewHolder(view,chatBotListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ChatBotViewHolder holder, int position) {
        holder.present(messages.get(position));
    }

    @Override
    public int getItemCount() {
        return messages.size();
    }

}
