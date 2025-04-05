package com.elmepa.syllabus.lessons

import kotlinx.collections.immutable.ImmutableList

internal sealed class LessonsView {

    sealed interface State {
        data object Loading : State
        data class Content(val lessons: ImmutableList<String>) : State
        data object Error : State
    }

    sealed interface UIAction {
        data class LessonTap(val lessonName: String) : UIAction
    }

    sealed interface Effect {
        data class NavigateToLessonDetails(val lessonId: Int) : UIAction
    }
}
