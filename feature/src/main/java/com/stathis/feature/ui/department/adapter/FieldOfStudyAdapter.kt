package com.stathis.feature.ui.department.adapter

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
import com.stathis.feature.databinding.HolderFieldOfStudyBinding
import com.stathis.feature.databinding.HolderShimmerFieldOfStudyBinding
import com.stathis.model.department.FieldOfStudy
import com.stathis.model.general.ShimmerItem

class FieldOfStudyAdapter : ListAdapter<UiModel, FieldOfStudyViewHolder>(BaseDiffUtil<UiModel>()) {

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
        else -> R.layout.holder_empty_view
    }
}

class FieldOfStudyViewHolder(
    private val binding: ViewDataBinding
) : BaseViewHolder(binding) {

    override fun bind(data: UiModel) {
        binding.setVariable(BR.model, data)
    }
}