package com.elmepa.supportv2.ui.faq

import com.stathis.model.support.Faq

sealed class FaqView {

    sealed interface State {
        data object Loading : State
        data class Content(val faqs: List<Faq>) : State
        data class Error(val errorBody: String) : State
    }
}
