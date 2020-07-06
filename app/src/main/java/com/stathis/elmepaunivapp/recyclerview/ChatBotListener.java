package com.stathis.elmepaunivapp.recyclerview;

import android.view.View;

import com.stathis.elmepaunivapp.models.Message;

public interface ChatBotListener extends View.OnClickListener {

    void onChatReply(Message message);
}
