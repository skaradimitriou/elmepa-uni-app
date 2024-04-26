package com.stathis.core.util

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("setImageDrawable")
fun ImageView.setImageDrawable(drawable: Int) {
    this.setImageResource(drawable)
}

@BindingAdapter("loadImageUrl")
fun ImageView.loadImage(imageUrl: String) {
    Glide.with(this.context).load(imageUrl)
        .placeholder(com.stathis.core.R.color.shimmer_grey_lighter)
        .into(this)
}

@BindingAdapter("setPubDate")
fun TextView.setPubDate(text: String) {
    this.text = text.substringBefore('|')
}