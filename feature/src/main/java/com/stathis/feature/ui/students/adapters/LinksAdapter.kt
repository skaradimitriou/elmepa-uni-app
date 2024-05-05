package com.stathis.feature.ui.students.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.ListAdapter
import com.stathis.core.base.BaseDiffUtil
import com.stathis.core.base.BaseViewHolder
import com.stathis.core.base.UiModel
import com.stathis.feature.BR
import com.stathis.feature.R
import com.stathis.feature.databinding.HolderEmptyViewBinding
import com.stathis.feature.databinding.HolderLinkItemBinding
import com.stathis.feature.databinding.HolderShimmerLinkItemBinding
import com.stathis.model.general.ShimmerItem
import com.stathis.model.students.Link

class LinksAdapter(
    private val callback: LinksCallback
) : ListAdapter<UiModel, LinksViewHolder>(BaseDiffUtil<UiModel>()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LinksViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = when (viewType) {
            R.layout.holder_link_item -> {
                HolderLinkItemBinding.inflate(inflater, parent, false)
            }

            R.layout.holder_shimmer_link_item -> {
                HolderShimmerLinkItemBinding.inflate(inflater, parent, false)
            }

            else -> HolderEmptyViewBinding.inflate(inflater, parent, false)
        }
        return LinksViewHolder(view, callback)
    }

    override fun onBindViewHolder(holder: LinksViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun getItemViewType(position: Int) = when (getItem(position)) {
        is Link -> R.layout.holder_link_item
        is ShimmerItem -> R.layout.holder_shimmer_link_item
        else -> R.layout.holder_empty_view
    }
}

class LinksViewHolder(
    private val binding: ViewDataBinding,
    private val callback: LinksCallback
) : BaseViewHolder(binding) {

    override fun bind(data: UiModel) {
        when (data) {
            is Link -> {
                binding.setVariable(BR.model, data)
                binding.setVariable(BR.callback, callback)
            }
        }
    }
}

fun interface LinksCallback {
    fun onLinkTap(model: Link)
}