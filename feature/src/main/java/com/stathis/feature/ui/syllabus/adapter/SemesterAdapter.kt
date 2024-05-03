package com.stathis.feature.ui.syllabus.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.stathis.core.base.BaseDiffUtil
import com.stathis.core.base.BaseViewHolder
import com.stathis.core.base.UiModel
import com.stathis.feature.databinding.HolderSemesterItemBinding
import com.stathis.model.syllabus.Semester

class SemesterAdapter(
    private val callback: SemesterCallback
) : ListAdapter<UiModel, SemesterViewHolder>(BaseDiffUtil<UiModel>()) {

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

    override fun bind(data: UiModel) {
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