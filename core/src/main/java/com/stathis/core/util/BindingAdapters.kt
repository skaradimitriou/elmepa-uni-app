package com.stathis.core.util

import android.graphics.Paint
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.stathis.core.R

@BindingAdapter("loadImageUrl")
fun ImageView.loadImage(imageUrl: String) {
    Glide.with(this.context).load(imageUrl)
        .placeholder(R.color.shimmer_grey_lighter)
        .into(this)
}

@BindingAdapter("underline")
fun TextView.underline(underlined: Boolean) {
    if (underlined) this.paintFlags = Paint.UNDERLINE_TEXT_FLAG
}

@BindingAdapter("setPubDate")
fun TextView.setPubDate(text: String) {
    this.text = text.substringBefore('|')
}

@BindingAdapter("textAndVisibility")
fun TextView.setTextAndVisibility(text: String) {
    if (text.isEmpty()) {
        visibility = View.GONE
    } else {
        visibility = View.VISIBLE
        this.text = text
    }
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

@BindingAdapter("animateArrow")
fun ImageView.animateArrow(isExpanded: Boolean) {
    if (isExpanded) {
        animate().rotation(90f).start()
    } else {
        animate().rotation(0f).start()
    }
}