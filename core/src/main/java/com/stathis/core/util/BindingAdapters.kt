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
        .placeholder(R.color.shimmer_grey_lighter)
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

@BindingAdapter("setProfessorImage", "professorGender")
fun ImageView.setProfessorImage(url: String, gender: String) {
    val genderImg = when (gender) {
        resources.getString(R.string.male) -> R.drawable.male
        else -> R.drawable.female
    }

    Glide.with(context).load(url).error(genderImg).into(this)
}