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
import com.stathis.department.databinding.HolderDepmemberItemBinding
import com.stathis.department.databinding.HolderShimmerDepmemberItemBinding
import com.stathis.model.UiModel
import com.stathis.model.general.ShimmerItem
import com.stathis.model.personnel.Person

class DepMembersAdapter : ListAdapter<com.stathis.model.UiModel, DepMembersViewHolder>(BaseDiffUtil<UiModel>()) {

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
        else -> com.stathis.common.R.layout.holder_empty_view
    }
}

class DepMembersViewHolder(
    private val binding: ViewDataBinding
) : BaseViewHolder(binding) {

    override fun bind(data: com.stathis.model.UiModel) {
        binding.setVariable(BR.model, data)
    }
}