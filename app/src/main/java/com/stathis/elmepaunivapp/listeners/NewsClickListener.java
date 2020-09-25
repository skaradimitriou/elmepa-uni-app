package com.stathis.elmepaunivapp.listeners;

import android.view.View;

import com.stathis.elmepaunivapp.ui.announcements.model.Announcement;

public interface NewsClickListener extends View.OnClickListener {

    void onNewsClick(Announcement announcement);
}
