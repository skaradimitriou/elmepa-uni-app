package com.stathis.elmepaunivapp.ui.students.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.stathis.elmepaunivapp.R
import com.stathis.elmepaunivapp.abstraction.DiffItemClass
import com.stathis.elmepaunivapp.listeners.latest.ElmepaClickListener
import com.stathis.elmepaunivapp.model.LocalModel

class UsefulLinksAdapterKt(private val callback : ElmepaClickListener) : ListAdapter<LocalModel, UsefulLinksViewHolderKt>(DiffItemClass<LocalModel>()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsefulLinksViewHolderKt {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.useful_links_item_row,parent,false)
        return UsefulLinksViewHolderKt(view,callback)
    }

    override fun onBindViewHolder(holder: UsefulLinksViewHolderKt, position: Int) {
        holder.bindData(getItem(position))
    }
}
