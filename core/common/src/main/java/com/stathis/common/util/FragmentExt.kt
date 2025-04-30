package com.stathis.common.util

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Toast
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle

/**
 * Helper fun to simplify setting a screen title in a [Fragment].
 */

fun Fragment.setScreenTitle(title: String) {
    requireActivity().title = title
}

/**
 * Helper method to inflate a custom menu in a [Fragment].
 * @param menuId The menu resource to inflate.
 * @param respondItemId The menu item id to listen for.
 * @param callback The callback to execute when the menu item is selected
 */

fun Fragment.inflateCustomMenu(menuId: Int, respondItemId: Int, callback: (MenuItem) -> Unit) {
    val menuHost: MenuHost = requireActivity()
    menuHost.addMenuProvider(object : MenuProvider {
        override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
            menuInflater.inflate(menuId, menu)
        }

        override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
            return when (menuItem.itemId) {
                respondItemId -> {
                    callback.invoke(menuItem)
                    true
                }

                else -> false
            }
        }
    }, viewLifecycleOwner, Lifecycle.State.RESUMED)
}

/**
 * Helper fun to launch an email intent to a specific email address.
 */

fun Fragment.startEmailIntent(emailAddress: String) {
    val mailIntent = Intent(Intent.ACTION_SEND).apply {
        type = EMAIL_TYPE
        putExtra(Intent.EXTRA_EMAIL, arrayOf(emailAddress))
    }

    try {
        activity?.let { startActivity(Intent.createChooser(mailIntent, SEND_MAIL)) }
    } catch (ex: ActivityNotFoundException) {
        Toast.makeText(requireContext(), NO_CLIENTS_INSTALLED, Toast.LENGTH_SHORT).show()
    }
}

/**
 * Helper fun to launch a dial intent to a specific number.
 */

fun Fragment.startDialIntent(numberToDial: String) {
    val intent = Intent(Intent.ACTION_DIAL).apply {
        data = Uri.parse(numberToDial)
    }
    activity?.let { startActivity(intent) }
}

/**
 * Helper fun to launch a share plain text intent.
 */

fun Fragment.startShareIntent(subject: String, body: String) {
    val txtIntent = Intent(Intent.ACTION_SEND).apply {
        setType("text/plain")
        putExtra(Intent.EXTRA_SUBJECT, subject)
        putExtra(Intent.EXTRA_TEXT, body)
    }

    val intent = Intent.createChooser(txtIntent, "Share", null)
    activity?.let { startActivity(intent) }
}

/**
 * Helper fun to launch a browser intent.
 */

fun Fragment.startNativeBrowserIntent(url: String) {
    val intent = Intent(Intent.ACTION_VIEW).apply { data = Uri.parse(url) }
    activity?.let { startActivity(intent) }
}
