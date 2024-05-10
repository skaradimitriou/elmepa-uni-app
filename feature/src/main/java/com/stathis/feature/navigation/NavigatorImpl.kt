package com.stathis.feature.navigation

import android.app.Activity
import android.os.Bundle
import androidx.navigation.NavController
import com.stathis.feature.R
import javax.inject.Inject

/**
 * Implementation of [Navigator].
 */

class NavigatorImpl @Inject constructor(
    private val activity: Activity?,
    private val navController: NavController
) : Navigator {

    override fun goToScreen(action: NavigationAction?, bundle: Bundle?) = when (action) {
        NavigationAction.DASHBOARD -> navController.navigate(R.id.nav_home)
        NavigationAction.ANNOUNCEMENTS -> navController.navigate(R.id.announcementsFragment)
        NavigationAction.STUDENTS -> navController.navigate(R.id.studentsFragment)
        NavigationAction.DEPARTMENT -> navController.navigate(R.id.departmentFragment)
        NavigationAction.RESEARCH -> navController.navigate(R.id.researchFragment)
        NavigationAction.PERSONNEL -> navController.navigate(R.id.nav_personnel)
        NavigationAction.SYLLABUS -> navController.navigate(R.id.nav_syllabus)
        NavigationAction.LESSONS -> navController.navigate(R.id.lessonsFragment, bundle)
        NavigationAction.LESSON_DETAILS -> navController.navigate(
            R.id.lessonDetailsFragment, bundle
        )

        NavigationAction.WEBVIEW -> navController.navigate(R.id.webViewFragment, bundle)
        NavigationAction.CONTACT -> navController.navigate(R.id.contactFragment)
        NavigationAction.FAQ -> navController.navigate(R.id.faqFragment)
        NavigationAction.ABOUT_APP -> navController.navigate(R.id.aboutAppFragment)
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
}