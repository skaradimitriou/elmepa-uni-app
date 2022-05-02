package com.stathis.elmepaunivapp.ui.announcements.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.stathis.elmepaunivapp.R
import com.stathis.elmepaunivapp.abstraction.DiffItemClass
import com.stathis.elmepaunivapp.abstraction.LocalModel
import com.stathis.elmepaunivapp.callbacks.ElmepaClickListener
import com.stathis.elmepaunivapp.databinding.AnnouncementItemRowBinding
import com.stathis.elmepaunivapp.databinding.HolderAnnouncementShimmerItemBinding
import com.stathis.elmepaunivapp.databinding.HolderEmptyItemRowBinding
import com.stathis.elmepaunivapp.model.Announcement
import com.stathis.elmepaunivapp.model.ShimmerModel

class AnnouncementsAdapter(private val callback : ElmepaClickListener) : ListAdapter<LocalModel, AnnouncementsViewHolder>(DiffItemClass<LocalModel>()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnnouncementsViewHolder {
        val view = when(viewType){
            R.layout.announcement_item_row -> AnnouncementItemRowBinding.inflate(LayoutInflater.from(parent.context),parent,false)
            R.layout.holder_announcement_shimmer_item -> HolderAnnouncementShimmerItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
            else -> HolderEmptyItemRowBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        }
        return AnnouncementsViewHolder(view,callback)
    }

    override fun onBindViewHolder(holder: AnnouncementsViewHolder, position: Int) {
        holder.bindData(getItem(position))
    }

    override fun getItemViewType(position: Int): Int = when(getItem(position)){
        is Announcement -> R.layout.announcement_item_row
        is ShimmerModel -> R.layout.holder_announcement_shimmer_item
        else -> R.layout.holder_empty_item_row
    }
}