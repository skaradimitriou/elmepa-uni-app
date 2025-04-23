package com.elmepa.syllabus.programmes

import com.stathis.model.syllabus.Programme
import com.stathis.model.syllabus.ProgrammeType
import kotlinx.collections.immutable.persistentListOf

internal class ProgrammesView {

    sealed interface State {
        data object Loading : State

        data class Content(
            val selectedTabPosition: Int = 0,
            val programmes: List<Programme> = persistentListOf()
        ) : State

        data object Error : State
    }

    sealed interface UIAction {
        data class OnTabSelection(val tabIndex: Int) : UIAction

        data class OnSemesterClick(val semester: String) : UIAction
    }

    sealed interface Effect {
        data class ChangeSelectedTab(val tabIndex: Int) : Effect {

            /**
             * Helper fun to transform the tab position to a [ProgrammeType].
             */
            fun toProgrammeType() = when (tabIndex) {
                0 -> ProgrammeType.UNDERGRADUATE_MST
                1 -> ProgrammeType.POSTGRADUATE_MST
                else -> ProgrammeType.UNDEFINED
            }
        }

        data class NavigateToLessonList(val semester: String) : Effect
    }
}
