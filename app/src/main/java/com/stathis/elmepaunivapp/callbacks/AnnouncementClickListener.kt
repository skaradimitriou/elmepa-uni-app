package com.stathis.elmepaunivapp.callbacks

import com.stathis.elmepaunivapp.ui.announcements.model.Announcement

interface AnnouncementClickListener {
    fun onAnnouncementTap(announcement : Announcement)
}