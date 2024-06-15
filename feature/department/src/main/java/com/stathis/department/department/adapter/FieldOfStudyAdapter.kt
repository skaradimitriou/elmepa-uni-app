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
import com.stathis.department.databinding.HolderFieldOfStudyBinding
import com.stathis.department.databinding.HolderShimmerFieldOfStudyBinding
import com.stathis.model.UiModel
import com.stathis.model.department.FieldOfStudy
import com.stathis.model.general.ShimmerItem

class FieldOfStudyAdapter : ListAdapter<UiModel, FieldOfStudyViewHolder>(
    BaseDiffUtil<UiModel>()
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FieldOfStudyViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = when (viewType) {
            R.layout.holder_field_of_study -> {
                HolderFieldOfStudyBinding.inflate(inflater, parent, false)
            }

            R.layout.holder_shimmer_field_of_study -> {
                HolderShimmerFieldOfStudyBinding.inflate(inflater, parent, false)
            }

            else -> HolderEmptyViewBinding.inflate(inflater, parent, false)
        }
        return FieldOfStudyViewHolder(view)
    }

    override fun onBindViewHolder(holder: FieldOfStudyViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun getItemViewType(position: Int) = when (getItem(position)) {
        is FieldOfStudy -> R.layout.holder_field_of_study
        is ShimmerItem -> R.layout.holder_shimmer_field_of_study
        else -> com.stathis.common.R.layout.holder_empty_view
    }
}

class FieldOfStudyViewHolder(
    private val binding: ViewDataBinding
) : BaseViewHolder(binding) {

    override fun bind(data: UiModel) {
        binding.setVariable(BR.model, data)
    }
}