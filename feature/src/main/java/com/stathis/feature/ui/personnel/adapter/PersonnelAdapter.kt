package com.stathis.feature.ui.personnel.adapter

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
import com.stathis.feature.databinding.HolderPersonnelItemBinding
import com.stathis.feature.databinding.HolderShimmerPersonnelItemBinding
import com.stathis.model.general.ShimmerItem
import com.stathis.model.personnel.Person

class PersonnelAdapter(
    private val callback: PersonnelCallback
) : ListAdapter<UiModel, PersonnelViewHolder>(BaseDiffUtil<UiModel>()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonnelViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = when (viewType) {
            R.layout.holder_personnel_item -> {
                HolderPersonnelItemBinding.inflate(inflater, parent, false)
            }

            R.layout.holder_shimmer_personnel_item -> {
                HolderShimmerPersonnelItemBinding.inflate(inflater, parent, false)
            }

            else -> HolderEmptyViewBinding.inflate(inflater, parent, false)
        }
        return PersonnelViewHolder(view, callback)
    }

    override fun onBindViewHolder(holder: PersonnelViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun getItemViewType(position: Int): Int = when (getItem(position)) {
        is Person -> R.layout.holder_personnel_item
        is ShimmerItem -> R.layout.holder_shimmer_personnel_item
        else -> R.layout.holder_empty_view
    }
}

class PersonnelViewHolder(
    private val binding: ViewDataBinding,
    private val callback: PersonnelCallback
) : BaseViewHolder(binding) {

    override fun bind(data: UiModel) {
        when (data) {
            is Person -> {
                binding.setVariable(BR.model, data)
                binding.setVariable(BR.callback, callback)
            }
        }
    }
}

fun interface PersonnelCallback {
    fun onPersonTap(model: Person)
}