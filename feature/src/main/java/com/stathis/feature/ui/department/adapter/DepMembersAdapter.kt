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
import com.stathis.feature.databinding.HolderDepmemberItemBinding
import com.stathis.feature.databinding.HolderEmptyViewBinding
import com.stathis.feature.databinding.HolderShimmerDepmemberItemBinding
import com.stathis.model.general.ShimmerItem
import com.stathis.model.personnel.Person

class DepMembersAdapter : ListAdapter<UiModel, DepMembersViewHolder>(BaseDiffUtil<UiModel>()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DepMembersViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = when (viewType) {
            R.layout.holder_depmember_item -> {
                HolderDepmemberItemBinding.inflate(inflater, parent, false)
            }

            R.layout.holder_shimmer_depmember_item -> {
                HolderShimmerDepmemberItemBinding.inflate(inflater, parent, false)
            }

            else -> HolderEmptyViewBinding.inflate(inflater, parent, false)
        }
        return DepMembersViewHolder(view)
    }

    override fun onBindViewHolder(holder: DepMembersViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun getItemViewType(position: Int) = when (getItem(position)) {
        is Person -> R.layout.holder_depmember_item
        is ShimmerItem -> R.layout.holder_shimmer_depmember_item
        else -> R.layout.holder_empty_view
    }
}

class DepMembersViewHolder(
    private val binding: ViewDataBinding
) : BaseViewHolder(binding) {

    override fun bind(data: UiModel) {
        binding.setVariable(BR.model, data)
    }
}