package com.elmepa.syllabus.lessondetails

internal sealed class LessonDetailsView {

    sealed interface State {
        data object Loading : State

        data class Content(
            val lessonName: String,
            val commitment: String,
            val lessonDescription: String,
            val credits: Int
        ) : State

        data object Error : State
    }
}
