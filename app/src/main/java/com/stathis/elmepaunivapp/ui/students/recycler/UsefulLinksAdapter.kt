package com.stathis.elmepaunivapp.ui.students.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.stathis.elmepaunivapp.R
import com.stathis.elmepaunivapp.abstraction.DiffItemClass
import com.stathis.elmepaunivapp.callbacks.ElmepaClickListener
import com.stathis.elmepaunivapp.model.LocalModel

class UsefulLinksAdapter(private val callback : ElmepaClickListener) : ListAdapter<LocalModel, UsefulLinksViewHolder>(DiffItemClass<LocalModel>()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsefulLinksViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.useful_links_item_row,parent,false)
        return UsefulLinksViewHolder(view,callback)
    }

    override fun onBindViewHolder(holder: UsefulLinksViewHolder, position: Int) {
        holder.bindData(getItem(position))
    }
}
