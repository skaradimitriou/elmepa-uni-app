package com.stathis.announcements.events.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.ListAdapter
import com.stathis.announcements.BR
import com.stathis.announcements.R
import com.stathis.announcements.databinding.HolderAnnouncementShimmerItemBinding
import com.stathis.announcements.databinding.HolderEventItemBinding
import com.stathis.core.base.BaseDiffUtil
import com.stathis.core.base.BaseViewHolder
import com.stathis.core.databinding.HolderEmptyViewBinding
import com.stathis.model.UiModel
import com.stathis.model.announcements.Event
import com.stathis.model.general.ShimmerItem

class EventsAdapter(
    private val callback: EventsCallback
) : ListAdapter<UiModel, EventsViewHolder>(BaseDiffUtil<UiModel>()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = when (viewType) {
            R.layout.holder_event_item -> {
                HolderEventItemBinding.inflate(inflater, parent, false)
            }

            R.layout.holder_announcement_shimmer_item -> {
                HolderAnnouncementShimmerItemBinding.inflate(inflater, parent, false)
            }

            else -> HolderEmptyViewBinding.inflate(inflater, parent, false)
        }
        return EventsViewHolder(view, callback)
    }

    override fun onBindViewHolder(holder: EventsViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun getItemViewType(position: Int): Int = when (getItem(position)) {
        is Event -> R.layout.holder_event_item
        is ShimmerItem -> R.layout.holder_announcement_shimmer_item
        else -> com.stathis.core.R.layout.holder_empty_view
    }
}

class EventsViewHolder(
    private val binding: ViewDataBinding,
    private val callback: EventsCallback
) : BaseViewHolder(binding) {

    override fun bind(data: UiModel) {
        when (data) {
            is Event -> {
                binding.setVariable(BR.model, data)
                binding.setVariable(BR.callback, callback)
            }
        }
    }
}

fun interface EventsCallback {
    fun onEventTap(model: Event)
}