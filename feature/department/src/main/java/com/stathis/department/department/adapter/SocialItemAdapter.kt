package com.stathis.department.department.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.ListAdapter
import com.stathis.common.base.BaseDiffUtil
import com.stathis.common.base.BaseViewHolder
import com.stathis.common.databinding.HolderEmptyViewBinding
import com.stathis.department.BR
import com.stathis.department.R
import com.stathis.department.databinding.HolderShimmerSocialItemBinding
import com.stathis.department.databinding.HolderSocialItemBinding
import com.stathis.model.UiModel
import com.stathis.model.department.SocialItem
import com.stathis.model.general.ShimmerItem

class SocialItemAdapter(
    private val callback: SocialItemCallback
) : ListAdapter<UiModel, SocialItemViewHolder>(BaseDiffUtil<UiModel>()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SocialItemViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = when (viewType) {
            R.layout.holder_social_item -> {
                HolderSocialItemBinding.inflate(inflater, parent, false)
            }

            R.layout.holder_shimmer_social_item -> {
                HolderShimmerSocialItemBinding.inflate(inflater, parent, false)
            }

            else -> HolderEmptyViewBinding.inflate(inflater, parent, false)
        }
        return SocialItemViewHolder(view, callback)
    }

    override fun onBindViewHolder(holder: SocialItemViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun getItemViewType(position: Int) = when (getItem(position)) {
        is SocialItem -> R.layout.holder_social_item
        is ShimmerItem -> R.layout.holder_shimmer_social_item
        else -> com.stathis.common.R.layout.holder_empty_view
    }
}

class SocialItemViewHolder(
    private val binding: ViewDataBinding,
    private val callback: SocialItemCallback
) : BaseViewHolder(binding) {

    override fun bind(data: UiModel) {
        binding.setVariable(BR.model, data)
        binding.setVariable(BR.callback, callback)
    }
}

fun interface SocialItemCallback {
    fun onSocialItemTap(model: SocialItem)
}