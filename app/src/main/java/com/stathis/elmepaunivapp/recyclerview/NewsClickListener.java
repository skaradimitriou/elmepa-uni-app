package com.stathis.elmepaunivapp.recyclerview;

import android.view.View;

import com.stathis.elmepaunivapp.models.Announcement;

public interface NewsClickListener extends View.OnClickListener {

    void onNewsClick(Announcement announcement);
}
