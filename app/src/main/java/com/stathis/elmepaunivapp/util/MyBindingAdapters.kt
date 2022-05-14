package com.stathis.elmepaunivapp.util

import android.graphics.Paint
import android.os.Handler
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.stathis.elmepaunivapp.R
import com.stathis.elmepaunivapp.ui.students.recycler.CarouselAdapter

/**
 * Binding Adapters for ImageViews
 */

@BindingAdapter("loadImageUrl")
fun ImageView.loadImage(imageUrl : String){
    Glide.with(this.context).load(imageUrl).placeholder(R.color.shimmer_grey_lighter).into(this)
}

@BindingAdapter("setImgResource")
fun setImage(img : ImageView, gender: String){
    when(gender){
        img.context.resources.getString(R.string.male) -> img.setImageResource(R.drawable.male_professor)
        img.context.resources.getString(R.string.female) -> img.setImageResource(R.drawable.female_professor)
    }
}

@BindingAdapter("setImageSrc")
fun ImageView.setImageSrc(drawable: Int){
    this.setImageResource(drawable)
}

@BindingAdapter("loadLocalPhoto")
fun ImageView.loadLocalPhoto(photo: String){
    try {
        val myImage = this.context.resources.getIdentifier(photo, "drawable", "com.stathis.elmepaunivapp")
        this.setImageResource(myImage)
    }catch (e : Exception){
        this.setImageResource(R.mipmap.ic_launcher)
    }
}

/**
 * Binding Adapters for TextViews
 */

@BindingAdapter("underline")
fun TextView.underline(underlined : Boolean) {
    if(underlined) this.paintFlags = Paint.UNDERLINE_TEXT_FLAG
}

@BindingAdapter("setHtmlText")
fun TextView.setText(text: String) {
    this.text = text.toNonHtmlText()
}

@BindingAdapter("setPubDate")
fun TextView.setPubDate(text: String) {
    this.text = text.substringBefore('|')
}

/**
 * Others
 */

@BindingAdapter("setRibbonColor")
fun View.setRibbonColor(type : Boolean){
    when(type){
        true -> this.setBackgroundResource(R.color.lesson_blue)
        false -> this.setBackgroundResource(R.color.dark_orange)
    }
}

/*
 *  This is a temp solution to include a self sliding viewpager on top of Students/Dept Screen
 *  Will be refactored into a more secure implementation later on, because handler is deprecated.
 */

@BindingAdapter("setScrollableViewPager")
fun setScrollableViewPager(viewPager : ViewPager2, adapter : CarouselAdapter){
    val sliderHandler = Handler()

    val sliderRunnable = Runnable {
        when (viewPager.currentItem == adapter.itemCount - 1) {
            true -> viewPager.currentItem = 0
            else -> viewPager.currentItem = viewPager.currentItem + 1
        }
    }

    viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
        override fun onPageSelected(position: Int) {
            super.onPageSelected(position)
            sliderHandler.removeCallbacks(sliderRunnable)
            sliderHandler.postDelayed(sliderRunnable, 2500)
        }

        override fun onPageScrollStateChanged(state: Int) {
            when (state == ViewPager2.SCROLL_STATE_IDLE) {
                true -> sliderHandler.postDelayed(sliderRunnable, 2500)
            }
        }
    })
}