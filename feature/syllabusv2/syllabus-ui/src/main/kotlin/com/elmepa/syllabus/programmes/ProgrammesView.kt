package com.elmepa.syllabus.programmes

internal class ProgrammesView {

    sealed interface State {

        data object Loading : State
        data object Content : State
        data object Error : State
    }

    sealed interface UIAction {

    }

    sealed interface Effect {

    }
}
