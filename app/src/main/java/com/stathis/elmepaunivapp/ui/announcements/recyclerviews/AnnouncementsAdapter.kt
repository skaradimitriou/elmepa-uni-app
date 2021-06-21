package com.stathis.elmepaunivapp.ui.announcements.recyclerviews

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.stathis.elmepaunivapp.R
import com.stathis.elmepaunivapp.abstraction.DiffItemClass
import com.stathis.elmepaunivapp.callbacks.ElmepaClickListener
import com.stathis.elmepaunivapp.abstraction.LocalModel

class AnnouncementsAdapter(private val callback : ElmepaClickListener) : ListAdapter<LocalModel, AnnouncementsViewHolder>(DiffItemClass<LocalModel>()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnnouncementsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.announcement_item_row,parent,false)
        return AnnouncementsViewHolder(view,callback)
    }

    override fun onBindViewHolder(holder: AnnouncementsViewHolder, position: Int) {
        holder.bindData(getItem(position))
    }
}