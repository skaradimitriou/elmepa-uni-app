package com.stathis.core.util

import androidx.fragment.app.Fragment

/**
 * Helper fun to simplify setting a screen title in a [Fragment].
 */

fun Fragment.setScreenTitle(title: String) {
    requireActivity().title = title
}