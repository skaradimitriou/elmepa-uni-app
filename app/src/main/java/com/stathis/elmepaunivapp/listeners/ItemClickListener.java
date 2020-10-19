package com.stathis.elmepaunivapp.listeners;

import com.stathis.elmepaunivapp.ui.chatbot.model.Answer;
import com.stathis.elmepaunivapp.ui.chatbot.model.Message;
import com.stathis.elmepaunivapp.ui.chatbot.model.Question;

public interface ItemClickListener {

    void onMessageClick(Question question);
    void onAnswerClick(Answer answer);
}
