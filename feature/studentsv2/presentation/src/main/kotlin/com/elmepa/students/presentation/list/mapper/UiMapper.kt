package com.elmepa.students.presentation.list.mapper

import com.elmepa.students.presentation.list.StudentsView.UIAction
import com.elmepa.students.domain.model.StudentAction
import com.elmepa.students.domain.model.StudentScreen

internal fun StudentAction.toUiAction() = when (this) {
    is StudentAction.OpenInBrowser -> UIAction.OpenUrlInBrowser(url)
    is StudentAction.OpenInWebView -> UIAction.OpenUrlInWebView(title, url)

    is StudentAction.OpenInAppScreen if screen is StudentScreen.AcademicSchedule -> {
        UIAction.OpenAcademicSchedule
    }

    else -> null
}
