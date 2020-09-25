package com.stathis.elmepaunivapp.listeners;

import android.view.View;

import com.stathis.elmepaunivapp.ui.chatbot.model.Message;

public interface ChatBotListener extends View.OnClickListener {

    void onChatReply(Message message);
}
