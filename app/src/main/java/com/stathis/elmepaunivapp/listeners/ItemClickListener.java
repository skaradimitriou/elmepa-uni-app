package com.stathis.elmepaunivapp.listeners;

import com.stathis.elmepaunivapp.ui.chatbot.model.Answer;
import com.stathis.elmepaunivapp.ui.chatbot.model.Message;

public interface ItemClickListener {

    void onMessageClick(Object message);
    void onAnswerClick(Answer answer);
}
