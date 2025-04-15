package com.elmepa.syllabus.lessondetails

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.buildAnnotatedString
import androidx.core.text.HtmlCompat

internal sealed class LessonDetailsView {

    sealed interface State {
        data object Loading : State

        data class Content(
            val lessonName: String,
            val commitment: String,
            val lessonDescription: String,
            val credits: Int
        ) : State {

            val description: AnnotatedString = buildAnnotatedString {
                append(HtmlCompat.fromHtml(lessonDescription, HtmlCompat.FROM_HTML_MODE_LEGACY))
            }
        }

        data object Error : State
    }

    sealed interface UIAction {
        data object GoBack : UIAction
    }

    sealed interface Effect {
        data object GoBack : Effect
    }
}
