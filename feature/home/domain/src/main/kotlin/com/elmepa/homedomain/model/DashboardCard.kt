package com.elmepa.homedomain.model

data class DashboardCard(
    val seq: Int,
    val imageRes: Int,
    val title: String,
    val type: DashboardCardType
)

/**
 * Enum class used to represent the type of the [DashboardCard].
 */
enum class DashboardCardType {

    ANNOUNCEMENTS,
    EVENTS,
    STUDENTS,
    DEPARTMENT,
    RESEARCH,
    APPLICATION_FORMS,
    FAQ,
    CONTACT,
    ABOUT
}
