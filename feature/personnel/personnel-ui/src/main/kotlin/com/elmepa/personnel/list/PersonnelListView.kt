package com.elmepa.personnel.list

import com.elmepa.personnel.model.Person

internal class PersonnelListView {

    sealed interface State {
        data object Loading : State
        data class Content(val personnel: List<Person>) : State
        data object Error : State
    }

    sealed interface UIAction {
        data class SearchPersonByName(val query: String) : UIAction
        data class EmailOptionTap(val email: String) : UIAction
        data class ShareDetailsOptionTap(val person: Person) : UIAction
        data object Retry : UIAction
    }

    sealed interface Effect {
        data class SendEmail(val email: String) : Effect
        data class ShareInfo(val dataToShare: String) : Effect
    }
}
