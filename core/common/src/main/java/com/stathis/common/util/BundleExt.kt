package com.stathis.common.util

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.Parcelable
import java.io.Serializable

/**
 * Helper fun to getSerializable from an [Intent]
 */

inline fun <reified T : Serializable> Bundle.getSerializableFromBundle(key: String): T? {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        getSerializable(key, T::class.java)
    } else {
        getSerializable(key) as T
    }
}

/**
 * Helper fun to getParcelable from an [Intent]
 */

inline fun <reified T : Parcelable> Bundle.getParcelableFromBundle(key: String): T? {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        getParcelable(key, T::class.java)
    } else {
        getParcelable(key)
    }
}