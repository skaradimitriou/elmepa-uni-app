package com.stathis.core.util

fun String?.toNotNull() = this ?: ""
fun Int?.toNotNull() = this ?: 0
fun Long?.toNotNull() = this ?: 0L
fun Float?.toNotNull() = this ?: 0.0f
fun Double?.toNotNull() = this ?: 0.0
fun Boolean?.toNotNull() = this ?: false

