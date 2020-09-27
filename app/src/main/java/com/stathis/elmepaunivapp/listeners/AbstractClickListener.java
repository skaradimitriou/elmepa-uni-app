package com.stathis.elmepaunivapp.listeners;

import android.view.View;

import com.stathis.elmepaunivapp.ui.chatbot.model.Message;

public interface AbstractClickListener extends View.OnClickListener {

    void onItemClick(Object object);
}
