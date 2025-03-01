package com.elmepa.homeui.ext

import com.elmepa.homedomain.model.DashboardCardType
import com.stathis.model.navigation.NavigationAction

/**
 * Helper method to transform the Dashboard's screen option type
 * to the respective [NavigationAction] needed.
 */

fun DashboardCardType.toNavigationAction(): NavigationAction = when (this) {
    DashboardCardType.ANNOUNCEMENTS -> NavigationAction.ANNOUNCEMENTS
    DashboardCardType.EVENTS -> NavigationAction.EVENTS
    DashboardCardType.DEPARTMENT -> NavigationAction.DEPARTMENT
    DashboardCardType.STUDENTS -> NavigationAction.STUDENTS
    DashboardCardType.APPLICATION_FORMS -> NavigationAction.APPLICATION_FORMS
    DashboardCardType.RESEARCH -> NavigationAction.RESEARCH
    DashboardCardType.FAQ -> NavigationAction.FAQ
    DashboardCardType.CONTACT -> NavigationAction.CONTACT
    DashboardCardType.ABOUT -> NavigationAction.ABOUT_APP
}
