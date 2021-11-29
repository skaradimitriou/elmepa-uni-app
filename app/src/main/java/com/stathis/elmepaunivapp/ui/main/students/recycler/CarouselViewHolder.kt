package com.stathis.elmepaunivapp.ui.main.students.recycler

import android.util.Log
import android.view.View
import com.stathis.elmepaunivapp.abstraction.ElmepaViewHolder
import com.stathis.elmepaunivapp.abstraction.LocalModel
import com.stathis.elmepaunivapp.callbacks.ElmepaClickListener
import com.stathis.elmepaunivapp.ui.main.students.model.refactor.CarouselItem
import kotlinx.android.synthetic.main.holder_carousel_item.view.*
import java.lang.Exception

class CarouselViewHolder(itemView : View, callback : ElmepaClickListener) : ElmepaViewHolder(itemView,callback) {

    override fun present(data: LocalModel) {
        when(data){
            is CarouselItem -> {
                itemView.carousel_title.text = data.title

                try {
                    val myImage = itemView.resources.getIdentifier(data.imageResource, "drawable", "com.stathis.elmepaunivapp")
                    itemView.carousel_img.setImageResource(myImage)
                }catch (e : Exception){
                    //FIXME: Add default img
                }
            }
        }
    }
}