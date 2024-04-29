package com.stathis.feature.ui.syllabus.adapter

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
import com.stathis.feature.databinding.HolderSemesterItemBinding
import com.stathis.feature.databinding.HolderSemesterShimmerItemBinding
import com.stathis.model.general.ShimmerItem
import com.stathis.model.syllabus.Semester

class SemesterAdapter(
    private val callback: SemesterCallback
) : ListAdapter<UiModel, SemesterViewHolder>(BaseDiffUtil<UiModel>()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SemesterViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = when (viewType) {
            R.layout.holder_semester_item -> {
                HolderSemesterItemBinding.inflate(inflater, parent, false)
            }

            R.layout.holder_semester_shimmer_item -> {
                HolderSemesterShimmerItemBinding.inflate(inflater, parent, false)
            }

            else -> HolderEmptyViewBinding.inflate(inflater, parent, false)
        }
        return SemesterViewHolder(view, callback)
    }

    override fun onBindViewHolder(holder: SemesterViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun getItemViewType(position: Int): Int = when (getItem(position)) {
        is Semester -> R.layout.holder_semester_item
        is ShimmerItem -> R.layout.holder_semester_shimmer_item
        else -> R.layout.holder_empty_view
    }
}

class SemesterViewHolder(
    private val binding: ViewDataBinding,
    private val callback: SemesterCallback
) : BaseViewHolder(binding) {

    override fun bind(data: UiModel) {
        when (data) {
            is Semester -> {
                binding.setVariable(BR.model, data)
                binding.setVariable(BR.callback, callback)
            }
        }
    }
}

fun interface SemesterCallback {
    fun onSemesterTap(model: Semester)
}