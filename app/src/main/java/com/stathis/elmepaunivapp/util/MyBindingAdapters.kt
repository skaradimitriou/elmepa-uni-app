package com.stathis.elmepaunivapp.util

import android.os.Handler
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import androidx.viewpager2.widget.ViewPager2
import com.squareup.picasso.Picasso
import com.stathis.elmepaunivapp.R
import com.stathis.elmepaunivapp.ui.home.students.recycler.CarouselAdapter
import java.lang.Exception

/**
 * Binding Adapters for ImageViews
 */

@BindingAdapter("loadImageFromUrl")
fun ImageView.loadImg(url: String) {
    Picasso.get().load(url).into(this)
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
fun loadLocalPhoto(img : ImageView, photo: String){
    try {
        val myImage = img.context.resources.getIdentifier(photo, "drawable", "com.stathis.elmepaunivapp")
        img.setImageResource(myImage)
    }catch (e : Exception){
        img.setImageResource(R.mipmap.ic_launcher)
    }
}

/**
 * Binding Adapters for TextViews
 */

@BindingAdapter("setBackground")
fun setBackground(textView : TextView, type: String){
    when(type){
        textView.context.resources.getString(R.string.data) -> textView.setBackgroundColor(
            ContextCompat.getColor(textView.context, R.color.lesson_green))
        textView.context.resources.getString(R.string.mkt) -> textView.setBackgroundColor(
            ContextCompat.getColor(textView.context, R.color.lesson_blue))
        textView.context.resources.getString(R.string.ba) -> textView.setBackgroundColor(
            ContextCompat.getColor(textView.context, R.color.dark_orange))
    }
}

/**
 * Others
 */

@BindingAdapter("setRibbonColor")
fun View.setRibbonColor(type : Boolean){
    when(type){
        true -> this.setBackgroundResource(R.color.lesson_blue)
        false -> this.setBackgroundResource(R.color.orange)
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