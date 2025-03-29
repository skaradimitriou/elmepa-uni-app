package com.elmepa.personnel.depdetails

import com.stathis.model.common.Link
import com.stathis.model.department.DepMember

internal sealed class DepDetailsView {

    sealed interface State {
        data object Loading : State
        data class Content(val depMember: DepMember) : State
    }

    sealed interface UIAction {
        data class OpenLink(val link: Link) : UIAction
    }

    sealed interface Effect {
        data class OpenBrowser(val url: String) : Effect
        data class SendEmail(val email: String) : Effect
    }
}
