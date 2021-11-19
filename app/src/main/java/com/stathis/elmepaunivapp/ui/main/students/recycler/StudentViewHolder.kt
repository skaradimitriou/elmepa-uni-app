package com.stathis.elmepaunivapp.ui.main.students.recycler

import android.os.Handler
import android.view.View
import androidx.viewpager2.widget.ViewPager2
import androidx.viewpager2.widget.ViewPager2.SCROLL_STATE_IDLE
import com.stathis.elmepaunivapp.abstraction.ElmepaViewHolder
import com.stathis.elmepaunivapp.callbacks.ElmepaClickListener
import com.stathis.elmepaunivapp.abstraction.LocalModel
import com.stathis.elmepaunivapp.ui.main.students.model.refactor.CarouselParent
import com.stathis.elmepaunivapp.ui.main.students.model.refactor.NewStudentItem
import kotlinx.android.synthetic.main.holder_parent_horizontal_nested_item.view.*
import kotlinx.android.synthetic.main.holder_viewpager_item.view.*

class StudentViewHolder(itemView : View, val callback : ElmepaClickListener) : ElmepaViewHolder(itemView,callback) {

    private val sliderHandler = Handler()

    override fun present(data: LocalModel) {
        when(data){
            is NewStudentItem -> {
                itemView.nested_title.text = data.title
                val adapter = UsefulLinksAdapter(callback)
                adapter.submitList(data.list)
                itemView.nested_recycler.adapter = adapter
            }

            is CarouselParent -> {
                val adapter = CarouselAdapter(callback)
                adapter.submitList(data.list)
                itemView.carousel_viewpager.adapter = adapter

                val sliderRunnable = Runnable {
                    when (itemView.carousel_viewpager.currentItem == adapter.itemCount - 1) {
                        true -> itemView.carousel_viewpager.currentItem = 0
                        else -> itemView.carousel_viewpager.currentItem = itemView.carousel_viewpager.currentItem + 1
                    }
                }

                itemView.carousel_viewpager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                    override fun onPageSelected(position: Int) {
                        super.onPageSelected(position)
                        sliderHandler.removeCallbacks(sliderRunnable)
                        sliderHandler.postDelayed(sliderRunnable, 2500)
                    }

                    override fun onPageScrollStateChanged(state: Int) {
                        when (state == SCROLL_STATE_IDLE) {
                            true -> sliderHandler.postDelayed(sliderRunnable, 2500)
                        }
                    }
                })
            }
        }
    }


}
