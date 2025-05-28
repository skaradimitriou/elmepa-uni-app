package com.elmepa.news.announcements

import com.elmepa.news.model.Announcement

internal sealed class AnnouncementsView {

    sealed interface UIAction {
        data class OnAnnouncementTap(val announcement: Announcement) : UIAction
        data object Back : UIAction
    }

    sealed interface Effect {
        data class NavigateToDetails(val announcement: Announcement) : Effect
        data object Back : Effect
    }
}
