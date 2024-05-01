package com.stathis.elmepaunivapp.util

import android.os.Handler
import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.viewpager2.widget.ViewPager2
import com.stathis.elmepaunivapp.R
import com.stathis.elmepaunivapp.ui.students.recycler.CarouselAdapter

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

/**
 * Others
 */

@BindingAdapter("setRibbonColor")
fun View.setRibbonColor(type: Boolean) {
    when (type) {
        true -> this.setBackgroundResource(com.stathis.core.R.color.lesson_blue)
        false -> this.setBackgroundResource(com.stathis.core.R.color.dark_orange)
    }
}

/*
 *  This is a temp solution to include a self sliding viewpager on top of Students/Dept Screen
 *  Will be refactored into a more secure implementation later on, because handler is deprecated.
 */

@BindingAdapter("setScrollableViewPager")
fun setScrollableViewPager(viewPager: ViewPager2, adapter: CarouselAdapter) {
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
                else -> Unit
            }
        }
    })
}