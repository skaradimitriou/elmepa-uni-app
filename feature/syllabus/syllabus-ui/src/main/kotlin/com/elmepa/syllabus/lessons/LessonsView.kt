package com.elmepa.syllabus.lessons

import com.stathis.model.syllabus.Lesson
import kotlinx.collections.immutable.ImmutableList

internal sealed class LessonsView {

    sealed interface State {
        data object Init : State

        data class Loading(val semester: String? = null) : State

        data class Content(
            val semester: String,
            val informativeText: String,
            val lessons: ImmutableList<Lesson>
        ) : State

        data object Error : State
    }

    sealed interface UIAction {
        data class LessonTap(val lessonName: String) : UIAction
    }

    sealed interface Effect {
        data class NavigateToLessonDetails(val lessonName: String) : Effect
    }
}
