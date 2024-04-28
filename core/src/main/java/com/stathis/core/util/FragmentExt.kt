package com.stathis.core.util

import android.content.ActivityNotFoundException
import android.content.Intent
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.stathis.core.R

/**
 * Helper fun to simplify setting a screen title in a [Fragment].
 */

fun Fragment.setScreenTitle(title: String) {
    requireActivity().title = title
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