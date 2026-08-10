package com.elmepa.students.presentation.acadschedule

import com.stathis.model.UiModel

internal sealed class AcademicScheduleView {

    sealed interface State {
        data object Loading : State
        data class Content(val items: List<UiModel>) : State
        data object Error : State
    }

    sealed interface UIAction {
        data object OnBackArrowTap : UIAction
    }

    sealed interface Effect {
        data object GoBack : Effect
    }
}
