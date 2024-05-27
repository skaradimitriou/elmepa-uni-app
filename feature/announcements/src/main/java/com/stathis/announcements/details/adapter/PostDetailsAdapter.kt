package com.stathis.announcements.details.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.ListAdapter
import com.stathis.announcements.BR
import com.stathis.announcements.R
import com.stathis.announcements.databinding.HolderPostDetailsItemBinding
import com.stathis.announcements.databinding.HolderPostDetailsWebBinding
import com.stathis.core.base.BaseDiffUtil
import com.stathis.core.base.BaseViewHolder
import com.stathis.core.databinding.HolderEmptyViewBinding
import com.stathis.model.UiModel
import com.stathis.model.announcements.details.PostDetailsHeader
import com.stathis.model.announcements.details.PostDetailsHtmlContent

class PostDetailsAdapter : ListAdapter<UiModel, PostDetailsViewHolder>(BaseDiffUtil<UiModel>()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostDetailsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = when (viewType) {
            R.layout.holder_post_details_item -> {
                HolderPostDetailsItemBinding.inflate(inflater, parent, false)
            }

            R.layout.holder_post_details_web -> {
                HolderPostDetailsWebBinding.inflate(inflater, parent, false)
            }

            else -> {
                HolderEmptyViewBinding.inflate(inflater, parent, false)
            }
        }
        return PostDetailsViewHolder(view)
    }

    override fun onBindViewHolder(holder: PostDetailsViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun getItemViewType(position: Int) = when (getItem(position)) {
        is PostDetailsHeader -> R.layout.holder_post_details_item
        is PostDetailsHtmlContent -> R.layout.holder_post_details_web
        else -> com.stathis.core.R.layout.holder_empty_view
    }
}

class PostDetailsViewHolder(
    val binding: ViewDataBinding
) : BaseViewHolder(binding) {

    override fun bind(data: UiModel) {
        when (data) {
            is PostDetailsHeader, is PostDetailsHtmlContent -> {
                binding.setVariable(BR.model, data)
            }
        }
    }
}