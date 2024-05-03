package com.stathis.feature.ui.lessons.adapter

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
import com.stathis.feature.databinding.HolderLessonsHeaderItemBinding
import com.stathis.feature.databinding.LessonItemRowBinding
import com.stathis.model.syllabus.Lesson
import com.stathis.model.syllabus.LessonHeader

class LessonsAdapter : ListAdapter<UiModel, LessonsViewHolder>(BaseDiffUtil<UiModel>()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LessonsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = when (viewType) {
            R.layout.holder_lessons_header_item -> {
                HolderLessonsHeaderItemBinding.inflate(inflater, parent, false)
            }

            R.layout.lesson_item_row -> {
                LessonItemRowBinding.inflate(inflater, parent, false)
            }

            else -> HolderEmptyViewBinding.inflate(inflater, parent, false)
        }
        return LessonsViewHolder(view)
    }

    override fun onBindViewHolder(holder: LessonsViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun getItemViewType(position: Int): Int = when (getItem(position)) {
        is LessonHeader -> R.layout.holder_lessons_header_item
        is Lesson -> R.layout.lesson_item_row
        else -> R.layout.holder_empty_view
    }
}

class LessonsViewHolder(private val binding: ViewDataBinding) : BaseViewHolder(binding) {

    override fun bind(data: UiModel) {
        when (data) {
            is LessonHeader -> {
                binding.setVariable(BR.lessonHeader, data)
            }

            is Lesson -> {
                binding.setVariable(BR.model, data)
            }
        }
    }
}