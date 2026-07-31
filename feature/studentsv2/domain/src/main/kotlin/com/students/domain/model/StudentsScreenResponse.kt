package com.students.domain.model

data class StudentSection(
    val title: String,
    val elements: List<StudentDisplayItem>
)

data class StudentDisplayItem(
    val icon: Int,
    val title: String,
    val subtitle: String,
    val action: StudentAction
)

sealed interface StudentAction {

    data class OpenInWebView(val title: String, val url: String) : StudentAction
    data class OpenInBrowser(val url: String) : StudentAction
    data class OpenInAppScreen(val screen: StudentScreen) : StudentAction
    data object None : StudentAction
}

sealed interface StudentScreen {
    data object AcademicSchedule : StudentScreen
}
