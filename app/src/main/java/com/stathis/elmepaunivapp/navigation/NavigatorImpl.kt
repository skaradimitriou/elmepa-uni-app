package com.stathis.elmepaunivapp.navigation

import android.os.Bundle
import androidx.navigation.NavController
import com.stathis.elmepaunivapp.R
import com.stathis.model.navigation.NavigationAction
import timber.log.Timber
import javax.inject.Inject

/**
 * Implementation of [Navigator].
 */

class NavigatorImpl @Inject constructor(
    private val navController: NavController
) : Navigator {

    override fun goToScreen(action: NavigationAction?, bundle: Bundle?) = when (action) {
        NavigationAction.DASHBOARD -> navController.navigateSafe(R.id.nav_home)
        NavigationAction.ANNOUNCEMENTS -> navController.navigateSafe(R.id.announcementsFragment)
        NavigationAction.EVENTS -> navController.navigateSafe(R.id.eventsFragment)
        NavigationAction.POST_DETAILS -> navController.navigateSafe(
            R.id.postDetailsFragment,
            bundle
        )

        NavigationAction.STUDENTS -> navController.navigateSafe(R.id.studentsFragment)
        NavigationAction.DEPARTMENT -> navController.navigateSafe(R.id.departmentFragment)
        NavigationAction.RESEARCH -> navController.navigateSafe(R.id.researchFragment)
        NavigationAction.PERSONNEL -> navController.navigateSafe(R.id.nav_personnel)
        NavigationAction.SYLLABUS -> navController.navigateSafe(R.id.nav_syllabus)
        NavigationAction.LESSONS -> navController.navigateSafe(R.id.lessonsFragment, bundle)
        NavigationAction.LESSON_DETAILS -> navController.navigateSafe(
            R.id.lessonDetailsFragment, bundle
        )

        NavigationAction.WEBVIEW -> navController.navigateSafe(R.id.webViewFragment, bundle)
        NavigationAction.CONTACT -> navController.navigateSafe(R.id.contactFragment)
        NavigationAction.FAQ -> navController.navigateSafe(R.id.faqFragment)
        NavigationAction.ABOUT_APP -> navController.navigateSafe(R.id.aboutAppFragment)
        else -> Unit
    }

    override fun goBack() {
        navController.navigateUp()
    }

    override fun isAtHomeScreens(): Boolean {
        val currentDestination = navController.currentDestination?.id
        return listOf(
            R.id.nav_home,
            R.id.nav_personnel,
            R.id.nav_syllabus
        ).contains(currentDestination)
    }

    /**
     * Helper method to prevent creating multiple back stack entries for the same screen.
     */

    private fun NavController.navigateSafe(destination: Int, bundle: Bundle? = null) {
        try {
            val entry = getBackStackEntry(destination)
            Timber.d("Tried to navigate multiple times")
        } catch (e: Exception) {
            navigate(destination, bundle)
        }
    }
}
