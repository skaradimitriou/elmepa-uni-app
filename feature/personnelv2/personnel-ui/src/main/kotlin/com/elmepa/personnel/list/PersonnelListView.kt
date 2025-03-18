package com.elmepa.personnel.list

import com.elmepa.personnel.model.Person

internal class PersonnelListView {

    sealed interface State {
        data object Loading : State
        data class Content(val personnel: List<Person>) : State
        data object Error : State
    }
}
