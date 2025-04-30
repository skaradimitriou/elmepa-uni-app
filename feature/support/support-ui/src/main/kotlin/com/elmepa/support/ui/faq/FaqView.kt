package com.elmepa.support.ui.faq

import com.elmepa.support.model.Faq

sealed class FaqView {

    sealed interface State {
        data object Loading : State
        data class Content(val faqs: List<Faq>) : State
        data class Error(val errorBody: String) : State
    }

    sealed interface UIAction {
        data object Back : UIAction
    }

    sealed interface Effect {
        data object Back : Effect
    }
}
