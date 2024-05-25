package com.stathis.feature.ui.department.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.ListAdapter
import com.stathis.core.base.BaseDiffUtil
import com.stathis.core.base.BaseViewHolder
import com.stathis.model.UiModel
import com.stathis.feature.BR
import com.stathis.feature.R
import com.stathis.feature.databinding.HolderEmptyViewBinding
import com.stathis.feature.databinding.HolderShimmerSocialItemBinding
import com.stathis.feature.databinding.HolderSocialItemBinding
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
        else -> R.layout.holder_empty_view
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