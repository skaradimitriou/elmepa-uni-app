package com.stathis.elmepaunivapp.ui.main.students.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.stathis.elmepaunivapp.R
import com.stathis.elmepaunivapp.abstraction.DiffItemClass
import com.stathis.elmepaunivapp.abstraction.LocalModel
import com.stathis.elmepaunivapp.callbacks.ElmepaClickListener

class CarouselAdapter(private val callback : ElmepaClickListener) : ListAdapter<LocalModel, CarouselViewHolder>(DiffItemClass<LocalModel>()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarouselViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.holder_carousel_item,parent,false)
        return CarouselViewHolder(view,callback)
    }

    override fun onBindViewHolder(holder: CarouselViewHolder, position: Int) {
        holder.bindData(getItem(position))
    }
}