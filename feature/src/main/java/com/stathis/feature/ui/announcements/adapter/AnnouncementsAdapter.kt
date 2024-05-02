package com.stathis.feature.ui.announcements.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.ListAdapter
import com.stathis.core.base.BaseDiffUtil
import com.stathis.core.base.BaseViewHolder
import com.stathis.core.base.UiModel
import com.stathis.feature.BR
import com.stathis.feature.R
import com.stathis.feature.databinding.HolderAnnouncementItemBinding
import com.stathis.feature.databinding.HolderAnnouncementShimmerItemBinding
import com.stathis.feature.databinding.HolderEmptyViewBinding
import com.stathis.model.announcements.Announcement
import com.stathis.model.general.ShimmerItem

class AnnouncementsAdapter(
    private val callback: AnnouncementsCallback
) : ListAdapter<UiModel, AnnouncementsViewHolder>(BaseDiffUtil<UiModel>()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnnouncementsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = when (viewType) {
            R.layout.holder_announcement_item -> {
                HolderAnnouncementItemBinding.inflate(inflater, parent, false)
            }

            R.layout.holder_announcement_shimmer_item -> {
                HolderAnnouncementShimmerItemBinding.inflate(inflater, parent, false)
            }

            else -> {
                HolderEmptyViewBinding.inflate(inflater, parent, false)
            }
        }
        return AnnouncementsViewHolder(view, callback)
    }

    override fun onBindViewHolder(holder: AnnouncementsViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun getItemViewType(position: Int): Int = when (getItem(position)) {
        is Announcement -> R.layout.holder_announcement_item
        is ShimmerItem -> R.layout.holder_announcement_shimmer_item
        else -> R.layout.holder_empty_view
    }
}

class AnnouncementsViewHolder(
    private val binding: ViewDataBinding,
    private val callback: AnnouncementsCallback
) : BaseViewHolder(binding) {

    override fun bind(data: UiModel) {
        when (data) {
            is Announcement -> {
                binding.setVariable(BR.model, data)
                binding.setVariable(BR.callback, callback)

                (binding as HolderAnnouncementItemBinding).announcementImgView.setOnLongClickListener {
                    callback.onLongAnnouncementTap(data)
                    true
                }
            }
        }
    }
}

interface AnnouncementsCallback {
    fun onAnnouncementTap(model: Announcement)
    fun onLongAnnouncementTap(model: Announcement)
}