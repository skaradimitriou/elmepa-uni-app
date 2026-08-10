package com.elmepa.students.presentation.list

import com.elmepa.students.domain.model.StudentSection

internal sealed class StudentsView {

    sealed interface State {
        data object Loading : State
        data class Content(val sections: List<StudentSection>) : State
        data object Error : State
    }

    sealed interface UIAction {
        data object Back : UIAction
        data object Retry : UIAction
        data class OpenUrlInWebView(val title: String, val url: String) : UIAction
        data class OpenUrlInBrowser(val url: String) : UIAction
        data object OpenAcademicSchedule : UIAction
    }

    sealed interface Effect {
        data object Back : Effect
        data class OpenUrlInWebView(val title: String, val url: String) : Effect
        data class OpenUrlInBrowser(val url: String) : Effect
        data object OpenAcademicSchedule : Effect
    }
}
