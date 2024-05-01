package com.stathis.core.util

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
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.stathis.core.R

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

fun Fragment.showProfessorDialog(message: String, email: String) {
    MaterialAlertDialogBuilder(requireContext()).apply {
        setTitle(getString(R.string.dialog_new_email))
        setMessage(message)
        setPositiveButton(getString(R.string.dialog_yes)) { dialog, which ->
            val i = Intent(Intent.ACTION_SEND)
                .setType(EMAIL_TYPE)
                .putExtra(Intent.EXTRA_EMAIL, arrayOf(email))

            try {
                startActivity(Intent.createChooser(i, SEND_MAIL))
            } catch (ex: ActivityNotFoundException) {
                Toast.makeText(requireContext(), NO_CLIENTS_INSTALLED, Toast.LENGTH_SHORT).show()
            }
        }
        setNegativeButton(getString(R.string.dialog_cancel)) { dialog, which -> dialog.dismiss() }
    }.show()
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
        startActivity(Intent.createChooser(mailIntent, SEND_MAIL))
    } catch (ex: ActivityNotFoundException) {
        //
    }
}

/**
 * Helper fun to launch a dial intent to a specific number.
 */

fun Fragment.startDialIntent(numberToDial: String) {
    startActivity(Intent(Intent.ACTION_DIAL).apply {
        data = Uri.parse(numberToDial)
    })
}