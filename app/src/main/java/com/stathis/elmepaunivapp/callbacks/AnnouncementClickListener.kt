package com.stathis.elmepaunivapp.callbacks

import com.stathis.elmepaunivapp.model.Announcement

interface AnnouncementClickListener {
    fun onAnnouncementTap(announcement : Announcement)
}