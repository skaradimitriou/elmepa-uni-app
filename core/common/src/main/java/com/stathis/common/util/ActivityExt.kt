package com.stathis.common.util

import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar

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

/**
 * Helper method to build and show a snackbar.
 * @param view => The view that it will be shown on.
 * @param title => The title that it will be visible on the [Snackbar].
 * @param actionText => The snackBar's action button.
 * @param callback => callback for the action button.
 */

fun AppCompatActivity.buildAndShowSnackBar(
    view: View,
    title: String,
    actionText: String? = null,
    callback: (() -> Unit)? = null
) {
    Snackbar
        .make(view, title, Snackbar.LENGTH_LONG)
        .setAction(actionText) {
            callback?.invoke()
        }.show()
}