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
    Glide.with(this.context)
        .load(imageUrl)
        .placeholder(R.drawable.placeholder)
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

@BindingAdapter("setPersonnelImage", "personnelGender")
fun ImageView.setPersonnelImage(url: String, gender: String) {
    val genderImg = when (gender) {
        resources.getString(R.string.male) -> R.drawable.male
        else -> R.drawable.female
    }

    Glide.with(context).load(url).placeholder(R.drawable.placeholder).error(genderImg).into(this)
}

@BindingAdapter("setPersonnelDescription")
fun TextView.setPersonnelDescription(description: String) {
    visibility = if (description.isEmpty()) {
        View.GONE
    } else {
        View.VISIBLE
    }

    text = description
}

@BindingAdapter("animateArrow")
fun ImageView.animateArrow(isExpanded: Boolean) {
    if (isExpanded) {
        animate().rotation(90f).start()
    } else {
        animate().rotation(0f).start()
    }
}

@BindingAdapter("setHtmlText")
fun TextView.setHtmlText(text: String) {
    this.text = text.toNonHtmlText()
}

@BindingAdapter("setRibbonColor")
fun View.setRibbonColor(isMandatory: Boolean) {
    if (isMandatory) {
        setBackgroundResource(R.color.lesson_blue)
    } else {
        setBackgroundResource(R.color.lesson_orange)
    }
}