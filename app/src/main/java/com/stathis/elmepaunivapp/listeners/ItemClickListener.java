package com.stathis.elmepaunivapp.listeners;

import com.stathis.elmepaunivapp.ui.chatbot.model.Answer;

public interface ItemClickListener {

    void onMessageClick(Object message);
    void onAnswerClick(Answer answer);
}
