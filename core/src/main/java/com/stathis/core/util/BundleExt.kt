package com.stathis.core.util

import android.content.Intent
import android.os.Build
import android.os.Bundle
import java.io.Serializable

/**
 * Helper fun to getSerializable from an [Intent]
 */

inline fun <reified T : Serializable> Bundle.getSerializable(key: String): T? {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        getSerializable(key, T::class.java)
    } else {
        getSerializable(key) as T
    }
}