package com.stathis.news.announcements.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.paging.PagingDataAdapter
import com.stathis.common.base.BaseDiffUtil
import com.stathis.common.base.BaseViewHolder
import com.stathis.model.announcements.Announcement
import com.stathis.news.BR
import com.stathis.news.databinding.HolderAnnouncementItemBinding

class AnnouncementsAdapter(
    private val callback: AnnouncementsCallback
) : PagingDataAdapter<Announcement, AnnouncementsViewHolder>(BaseDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnnouncementsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = HolderAnnouncementItemBinding.inflate(inflater, parent, false)
        return AnnouncementsViewHolder(view, callback)
    }

    override fun onBindViewHolder(holder: AnnouncementsViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }
}

class AnnouncementsViewHolder(
    private val binding: ViewDataBinding,
    private val callback: AnnouncementsCallback
) : BaseViewHolder(binding) {

    override fun bind(data: com.stathis.model.UiModel) {
        when (data) {
            is Announcement -> {
                binding.setVariable(BR.model, data)
                binding.setVariable(BR.callback, callback)
            }
        }
    }
}

fun interface AnnouncementsCallback {
    fun onAnnouncementTap(model: Announcement)
}