package com.stathis.feature.navigation

import android.os.Bundle
import com.stathis.model.navigation.NavigationAction

/**
 * Navigator is a Helper class used for the app's navigation purposes
 * according to specific actions ([NavigationAction]).
 *
 * Each behaviour is bound to a unique action.
 */

interface Navigator {

    fun goToScreen(action: NavigationAction?, bundle: Bundle? = null)

    fun goBack()

    fun isAtHomeScreens() : Boolean
}