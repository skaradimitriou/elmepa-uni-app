package com.elmepa.supportv2.ui.applicationforms

import com.elmepa.supportv2.model.ApplicationForm

internal sealed class ApplicationFormsView {

    sealed interface State {
        data object Loading : State
        data class Content(val forms: List<ApplicationForm>) : State
        data object Error : State
    }

    sealed interface UIAction {
        data class OpenForm(val url: String) : UIAction
    }

    sealed interface Effect {
        data class OpenBrowser(val url: String) : Effect
    }
}
