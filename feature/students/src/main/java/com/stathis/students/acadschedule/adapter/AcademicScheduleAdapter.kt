package com.stathis.students.acadschedule.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.ListAdapter
import com.stathis.common.base.BaseDiffUtil
import com.stathis.common.base.BaseViewHolder
import com.stathis.common.databinding.HolderEmptyViewBinding
import com.stathis.model.UiModel
import com.stathis.model.general.ShimmerItem
import com.stathis.model.students.AcademicScheduleEntry
import com.stathis.model.students.AcademicScheduleTitle
import com.stathis.students.BR
import com.stathis.students.R
import com.stathis.students.databinding.HolderAcadScheduleEntryBinding
import com.stathis.students.databinding.HolderAcadScheduleTitleBinding
import com.stathis.students.databinding.HolderShimmerAcadScheduleItemBinding

class AcademicScheduleAdapter :
    ListAdapter<UiModel, AcademicScheduleViewHolder>(BaseDiffUtil<UiModel>()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AcademicScheduleViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = when (viewType) {
            R.layout.holder_shimmer_acad_schedule_item -> {
                HolderShimmerAcadScheduleItemBinding.inflate(inflater, parent, false)
            }

            R.layout.holder_acad_schedule_title -> {
                HolderAcadScheduleTitleBinding.inflate(inflater, parent, false)
            }

            R.layout.holder_acad_schedule_entry -> {
                HolderAcadScheduleEntryBinding.inflate(inflater, parent, false)
            }

            else -> HolderEmptyViewBinding.inflate(inflater, parent, false)
        }

        return AcademicScheduleViewHolder(view)
    }

    override fun onBindViewHolder(holder: AcademicScheduleViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun getItemViewType(position: Int) = when (getItem(position)) {
        is ShimmerItem -> R.layout.holder_shimmer_acad_schedule_item
        is AcademicScheduleTitle -> R.layout.holder_acad_schedule_title
        is AcademicScheduleEntry -> R.layout.holder_acad_schedule_entry
        else -> com.stathis.common.R.layout.holder_empty_view
    }
}

class AcademicScheduleViewHolder(
    private val binding: ViewDataBinding
) : BaseViewHolder(binding) {

    override fun bind(data: UiModel) {
        when (data) {
            is AcademicScheduleTitle, is AcademicScheduleEntry -> {
                binding.setVariable(BR.model, data)
            }

            else -> Unit
        }
    }
}