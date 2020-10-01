package com.stathis.elmepaunivapp.recyclerviews;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.stathis.elmepaunivapp.R;
import com.stathis.elmepaunivapp.abstraction.AbstractViewHolder;
import com.stathis.elmepaunivapp.abstraction.DiffItemCallbackClass;
import com.stathis.elmepaunivapp.listeners.ChatBotListener;
import com.stathis.elmepaunivapp.listeners.ItemClickListener;
import com.stathis.elmepaunivapp.ui.chatbot.model.Answer;
import com.stathis.elmepaunivapp.ui.chatbot.model.Message;
import com.stathis.elmepaunivapp.ui.chatbot.model.Question;

import java.util.ArrayList;
import java.util.List;

public class ChatBotAdapter extends ListAdapter<Object, AbstractViewHolder> {

    private ItemClickListener itemClickListener;

    public ChatBotAdapter(@NonNull ItemClickListener diffCallback) {
        super(new DiffItemCallbackClass<Object>());
    }


    @NonNull
    @Override
    public AbstractViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(viewType, parent, false);
        if (viewType == R.layout.holder_question_item) {
            return new MessageViewHolder(view, itemClickListener);
        }else if (viewType == R.layout.holder_answer_item){
            return new AnswerViewHolder(view,itemClickListener);
        } else {
            return null;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull AbstractViewHolder holder, int position) {
        if (getItem(position) instanceof Question) {
            holder.present(getItem(position));
        } else if (getItem(position) instanceof Answer) {
            holder.present(getItem(position));
        } else {
            //
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (getItem(position) instanceof Question) {
            return R.layout.holder_question_item;
        } else if (getItem(position) instanceof Answer) {
            return R.layout.holder_answer_item;
        } else {
            return 3;
        }
    }
}
