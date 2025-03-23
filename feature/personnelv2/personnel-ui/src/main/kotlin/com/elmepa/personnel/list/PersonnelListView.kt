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
        data class PersonTap(val person: Person) : UIAction
    }

    sealed interface Effect {
        data class OpenBottomSheet(val person: Person) : Effect
    }
}
