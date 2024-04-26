package com.stathis.feature.util

import com.stathis.feature.navigation.NavigationAction
import com.stathis.model.home.OptionType

/**
 * Helper method to transform the Dashboard's screen option type
 * to the respective [NavigationAction] needed.
 */

fun OptionType.toNavigationAction(): NavigationAction = when (this) {
    OptionType.ANNOUNCEMENTS -> NavigationAction.ANNOUNCEMENTS
    OptionType.DEPARTMENT -> NavigationAction.DEPARTMENT
    OptionType.STUDENTS -> NavigationAction.STUDENTS
    OptionType.RESEARCH -> NavigationAction.RESEARCH
}