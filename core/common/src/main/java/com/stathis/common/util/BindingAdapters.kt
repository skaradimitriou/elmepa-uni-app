package com.stathis.common.util

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.stathis.common.R

@BindingAdapter("loadImageUrl")
fun ImageView.loadImage(imageUrl: String? = null) {
    Glide.with(this.context)
        .load(imageUrl)
        .placeholder(R.drawable.placeholder)
        .into(this)
}
