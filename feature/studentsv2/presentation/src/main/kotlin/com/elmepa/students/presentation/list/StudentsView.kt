package com.elmepa.students.presentation.list

internal sealed class StudentsView {

    sealed interface State {
        data object Loading : State
        data class Content(val items: List<String>) : State
        data object Error : State
    }

    sealed interface UIAction {
        data object Back : UIAction
        data object Retry : UIAction
    }

    sealed interface Effect {
        data object Back : Effect
    }
}
