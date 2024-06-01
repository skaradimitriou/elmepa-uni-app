package com.stathis.department.department.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.ListAdapter
import com.stathis.core.base.BaseDiffUtil
import com.stathis.core.base.BaseViewHolder
import com.stathis.core.databinding.HolderEmptyViewBinding
import com.stathis.department.BR
import com.stathis.department.R
import com.stathis.department.databinding.HolderProgrammeItemBinding
import com.stathis.department.databinding.HolderShimmerProgrammeItemBinding
import com.stathis.model.UiModel
import com.stathis.model.department.Programme
import com.stathis.model.general.ShimmerItem

class ProgrammeAdapter(
    private val callback: ProgrammeCallback
) : ListAdapter<UiModel, ProgrammeViewHolder>(BaseDiffUtil<UiModel>()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProgrammeViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = when (viewType) {
            R.layout.holder_programme_item -> {
                HolderProgrammeItemBinding.inflate(inflater, parent, false)
            }

            R.layout.holder_shimmer_programme_item -> {
                HolderShimmerProgrammeItemBinding.inflate(inflater, parent, false)
            }

            else -> HolderEmptyViewBinding.inflate(inflater, parent, false)
        }
        return ProgrammeViewHolder(view, callback)
    }

    override fun onBindViewHolder(holder: ProgrammeViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun getItemViewType(position: Int) = when (getItem(position)) {
        is Programme -> R.layout.holder_programme_item
        is ShimmerItem -> R.layout.holder_shimmer_programme_item
        else -> com.stathis.core.R.layout.holder_empty_view
    }
}

class ProgrammeViewHolder(
    private val binding: ViewDataBinding,
    private val callback: ProgrammeCallback
) : BaseViewHolder(binding) {

    override fun bind(data: UiModel) {
        binding.setVariable(BR.model, data)
        binding.setVariable(BR.callback, callback)
    }
}

fun interface ProgrammeCallback {
    fun onProgrammeTap(model: Programme)
}