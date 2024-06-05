package com.stathis.personnel.ui.personnel.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.ListAdapter
import com.stathis.common.base.BaseDiffUtil
import com.stathis.common.base.BaseViewHolder
import com.stathis.common.databinding.HolderEmptyViewBinding
import com.stathis.model.UiModel
import com.stathis.model.general.ShimmerItem
import com.stathis.model.personnel.Person
import com.stathis.personnel.BR
import com.stathis.personnel.R
import com.stathis.personnel.databinding.HolderPersonnelItemBinding
import com.stathis.personnel.databinding.HolderShimmerPersonnelItemBinding

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
        else -> com.stathis.common.R.layout.holder_empty_view
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