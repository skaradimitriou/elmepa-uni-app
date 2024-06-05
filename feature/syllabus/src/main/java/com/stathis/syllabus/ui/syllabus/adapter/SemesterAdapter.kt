package com.stathis.syllabus.ui.syllabus.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.stathis.common.base.BaseDiffUtil
import com.stathis.common.base.BaseViewHolder
import com.stathis.model.UiModel
import com.stathis.model.syllabus.Semester
import com.stathis.syllabus.databinding.HolderSemesterItemBinding

class SemesterAdapter(
    private val callback: SemesterCallback
) : ListAdapter<com.stathis.model.UiModel, SemesterViewHolder>(BaseDiffUtil<UiModel>()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SemesterViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = HolderSemesterItemBinding.inflate(inflater, parent, false)
        return SemesterViewHolder(view, callback)
    }

    override fun onBindViewHolder(holder: SemesterViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

class SemesterViewHolder(
    private val binding: HolderSemesterItemBinding,
    private val callback: SemesterCallback
) : BaseViewHolder(binding) {

    override fun bind(data: com.stathis.model.UiModel) {
        when (data) {
            is Semester -> {
                binding.model = data
                binding.callback = callback
            }
        }
    }
}

fun interface SemesterCallback {
    fun onSemesterTap(model: Semester)
}