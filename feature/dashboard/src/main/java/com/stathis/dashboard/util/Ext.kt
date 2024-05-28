package com.stathis.dashboard.util

import com.stathis.model.home.OptionType
import com.stathis.model.navigation.NavigationAction

/**
 * Helper method to transform the Dashboard's screen option type
 * to the respective [NavigationAction] needed.
 */

fun OptionType.toNavigationAction(): NavigationAction = when (this) {
    OptionType.ANNOUNCEMENTS -> NavigationAction.ANNOUNCEMENTS
    OptionType.EVENTS -> NavigationAction.EVENTS
    OptionType.DEPARTMENT -> NavigationAction.DEPARTMENT
    OptionType.STUDENTS -> NavigationAction.STUDENTS
    OptionType.RESEARCH -> NavigationAction.RESEARCH
    OptionType.FAQ -> NavigationAction.FAQ
    OptionType.CONTACT -> NavigationAction.CONTACT
    OptionType.ABOUT -> NavigationAction.ABOUT_APP
}