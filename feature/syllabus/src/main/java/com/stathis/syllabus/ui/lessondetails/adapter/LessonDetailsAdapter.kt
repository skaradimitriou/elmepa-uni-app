package com.stathis.syllabus.ui.lessondetails.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.stathis.core.base.BaseDiffUtil
import com.stathis.core.base.BaseViewHolder
import com.stathis.model.UiModel
import com.stathis.model.syllabus.Lesson
import com.stathis.syllabus.BR
import com.stathis.syllabus.databinding.HolderLessonDetailsBinding

class LessonDetailsAdapter :
    ListAdapter<UiModel, LessonDetailsViewHolder>(BaseDiffUtil<UiModel>()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LessonDetailsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = HolderLessonDetailsBinding.inflate(inflater, parent, false)
        return LessonDetailsViewHolder(view)
    }

    override fun onBindViewHolder(holder: LessonDetailsViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

class LessonDetailsViewHolder(
    private val binding: HolderLessonDetailsBinding
) : BaseViewHolder(binding) {

    override fun bind(data: UiModel) {
        when (data) {
            is Lesson -> {
                binding.setVariable(BR.model, data)
            }
        }
    }
}