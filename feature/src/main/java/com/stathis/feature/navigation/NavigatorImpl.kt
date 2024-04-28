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
        NavigationAction.DASHBOARD -> navController.navigate(R.id.homeFragment)
        NavigationAction.ANNOUNCEMENTS -> navController.navigate(R.id.announcementsFragment)
        NavigationAction.RESEARCH -> navController.navigate(R.id.researchFragment)
        NavigationAction.PROFESSORS -> navController.navigate(R.id.professorFragment)
        NavigationAction.WEBVIEW -> navController.navigate(R.id.webViewFragment, bundle)
        else -> Unit
    }

    override fun goBack() {
        navController.navigateUp()
    }
}