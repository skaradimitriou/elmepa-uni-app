package com.elmepa.students.presentation.list

import com.students.domain.model.StudentSection

internal sealed class StudentsView {

    sealed interface State {
        data object Loading : State
        data class Content(val sections: List<StudentSection>) : State
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
