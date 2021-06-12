package com.stathis.elmepaunivapp.ui.syllabus.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.stathis.elmepaunivapp.R
import com.stathis.elmepaunivapp.abstraction.DiffItemClass
import com.stathis.elmepaunivapp.listeners.latest.ElmepaClickListener
import com.stathis.elmepaunivapp.model.LocalModel

class SemesterAdapter(private val callback : ElmepaClickListener) : ListAdapter<LocalModel, SemesterViewHolder>(DiffItemClass<LocalModel>()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SemesterViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.holder_semester_item_row,parent,false)
        return SemesterViewHolder(view,callback)
    }

    override fun onBindViewHolder(holder: SemesterViewHolder, position: Int) {
        holder.bindData(getItem(position))
    }
}