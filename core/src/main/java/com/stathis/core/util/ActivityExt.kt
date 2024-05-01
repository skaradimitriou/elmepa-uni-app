package com.stathis.core.util

import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity

/**
 * Helper extention functions used in Activities inside the app.
 */

fun AppCompatActivity.onBackButtonClick(callback: () -> Unit) {
    onBackPressedDispatcher.addCallback(object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            callback.invoke()
        }
    })
}