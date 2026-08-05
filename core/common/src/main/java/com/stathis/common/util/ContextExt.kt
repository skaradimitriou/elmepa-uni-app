package com.stathis.common.util

import android.content.Context
import android.content.Intent
import androidx.core.net.toUri

/**
 * Helper fun to launch a browser intent.
 */
fun Context.launchBrowser(url: String) {
    val intent = Intent(Intent.ACTION_VIEW).apply { data = url.toUri() }
    startActivity(intent)
}
