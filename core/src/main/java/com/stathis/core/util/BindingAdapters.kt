package com.stathis.core.util

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.stathis.core.R

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

@BindingAdapter("loadLocalPhoto")
fun ImageView.loadLocalPhoto(photo: String) {
    try {
        val myImage =
            this.context.resources.getIdentifier(photo, "drawable", "com.stathis.elmepaunivapp")
        this.setImageResource(myImage)
    } catch (e: Exception) {
        this.setImageResource(R.mipmap.ic_launcher)
    }
}

@BindingAdapter("setGenderImage")
fun setImage(img: ImageView, gender: String) {
    when (gender) {
        img.context.resources.getString(R.string.male) -> img.setImageResource(R.drawable.male)
        img.context.resources.getString(R.string.female) -> img.setImageResource(R.drawable.female)
    }
}