package com.stathis.core.util

import android.widget.ImageView
import androidx.databinding.BindingAdapter

@BindingAdapter("setImageDrawable")
fun ImageView.setImageDrawable(drawable: Int) {
    this.setImageResource(drawable)
}