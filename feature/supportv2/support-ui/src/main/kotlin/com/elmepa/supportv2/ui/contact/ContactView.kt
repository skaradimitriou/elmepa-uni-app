package com.elmepa.supportv2.ui.contact

import com.elmepa.supportv2.model.ContactItem

internal class ContactView {

    sealed interface State {
        data object Loading : State
        data class Content(val contactList: List<ContactItem>) : State
        data object Error : State
    }

    sealed interface UIAction {
        data class CallSecretary(val telephoneNumber: String) : UIAction
        data class SendEmail(val email: String) : UIAction
        data class OpenUrl(val url: String) : UIAction
    }

    sealed interface Effect {
        data class OpenDialer(val telephoneNumber: String) : Effect
        data class OpenEmailProvider(val email: String) : Effect
        data class OpenUrl(val url: String) : Effect
    }
}
