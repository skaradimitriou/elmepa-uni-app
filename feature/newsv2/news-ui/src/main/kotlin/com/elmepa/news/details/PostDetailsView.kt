package com.elmepa.news.details

internal sealed class PostDetailsView {

    sealed interface State {
        data object Loading : State

        data class Content(
            val title: String,
            val image: String,
            val pubDate: String,
            val openUrl: String,
            val htmlContent: String
        ) : State

        data object Error : State
    }

    sealed interface UIAction {
        data object Back : UIAction
        data object Share : UIAction
    }

    sealed interface Effect {
        data object GoBack : Effect
        data class SharePost(
            val title: String,
            val openUrl: String
        ) : Effect
    }
}
