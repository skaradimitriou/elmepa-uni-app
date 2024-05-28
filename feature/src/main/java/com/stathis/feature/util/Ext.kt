package com.stathis.feature.util

import android.os.Handler
import androidx.databinding.BindingAdapter
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import com.stathis.feature.common.CarouselAdapter

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

    viewPager.setPageTransformer(MarginPageTransformer(30))

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