package com.elmepa.news.events

import com.stathis.model.announcements.Event

internal sealed class EventsView {

    sealed interface UIAction {
        data class OnEventTap(val event: Event) : UIAction
        data object Back : UIAction
    }

    sealed interface Effect {
        data class NavigateToDetails(val event: Event) : Effect
        data object Back : Effect
    }
}
