package com.stathis.elmepaunivapp.ui.main.department.recyclerviews

import android.os.Handler
import android.util.Log
import android.view.View
import androidx.viewpager2.widget.ViewPager2
import com.stathis.elmepaunivapp.abstraction.ElmepaViewHolder
import com.stathis.elmepaunivapp.callbacks.ElmepaClickListener
import com.stathis.elmepaunivapp.abstraction.LocalModel
import com.stathis.elmepaunivapp.ui.main.department.model.NewDepartmentItem
import com.stathis.elmepaunivapp.ui.main.students.model.refactor.CarouselParent
import com.stathis.elmepaunivapp.ui.main.students.recycler.CarouselAdapter
import kotlinx.android.synthetic.main.holder_parent_horizontal_nested_item.view.*
import kotlinx.android.synthetic.main.holder_viewpager_item.view.*

class DepartmentViewHolder(itemView : View,val callback : ElmepaClickListener) : ElmepaViewHolder(itemView,callback) {

    private val sliderHandler = Handler()

    override fun present(data: LocalModel) {
        when(data){
            is NewDepartmentItem -> {
                itemView.nested_title.text = data.title
                val adapter = DepartmentChildAdapter(callback)
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
                        when (state == ViewPager2.SCROLL_STATE_IDLE) {
                            true -> sliderHandler.postDelayed(sliderRunnable, 2500)
                        }
                    }
                })
            }
        }
    }
}