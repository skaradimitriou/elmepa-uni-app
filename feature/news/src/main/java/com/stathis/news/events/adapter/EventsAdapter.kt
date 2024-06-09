package com.stathis.news.events.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.paging.PagingDataAdapter
import com.stathis.common.base.BaseDiffUtil
import com.stathis.common.base.BaseViewHolder
import com.stathis.model.UiModel
import com.stathis.model.announcements.Event
import com.stathis.news.BR
import com.stathis.news.databinding.HolderEventItemBinding

class EventsAdapter(
    private val callback: EventsCallback
) : PagingDataAdapter<Event, EventsViewHolder>(BaseDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = HolderEventItemBinding.inflate(inflater, parent, false)
        return EventsViewHolder(view, callback)
    }

    override fun onBindViewHolder(holder: EventsViewHolder, position: Int) {
        getItem(position)?.let { model -> holder.bind(model) }
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