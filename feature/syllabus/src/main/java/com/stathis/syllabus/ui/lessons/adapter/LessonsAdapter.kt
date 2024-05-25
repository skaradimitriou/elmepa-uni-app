package com.stathis.syllabus.ui.lessons.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.ListAdapter
import com.stathis.core.base.BaseDiffUtil
import com.stathis.core.base.BaseViewHolder
import com.stathis.model.UiModel
import com.stathis.model.syllabus.Lesson
import com.stathis.model.syllabus.LessonHeader
import com.stathis.syllabus.BR
import com.stathis.syllabus.R
import com.stathis.syllabus.databinding.HolderEmptyViewBinding
import com.stathis.syllabus.databinding.HolderLessonItemBinding
import com.stathis.syllabus.databinding.HolderLessonsHeaderItemBinding

class LessonsAdapter(
    private val callback: LessonCallback
) : ListAdapter<UiModel, LessonsViewHolder>(BaseDiffUtil<UiModel>()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LessonsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = when (viewType) {
            R.layout.holder_lessons_header_item -> {
                HolderLessonsHeaderItemBinding.inflate(inflater, parent, false)
            }

            R.layout.holder_lesson_item -> {
                HolderLessonItemBinding.inflate(inflater, parent, false)
            }

            else -> HolderEmptyViewBinding.inflate(inflater, parent, false)
        }
        return LessonsViewHolder(view, callback)
    }

    override fun onBindViewHolder(holder: LessonsViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun getItemViewType(position: Int): Int = when (getItem(position)) {
        is LessonHeader -> R.layout.holder_lessons_header_item
        is Lesson -> R.layout.holder_lesson_item
        else -> R.layout.holder_empty_view
    }
}

class LessonsViewHolder(
    private val binding: ViewDataBinding,
    private val callback: LessonCallback
) : BaseViewHolder(binding) {

    override fun bind(data: UiModel) {
        when (data) {
            is LessonHeader -> {
                binding.setVariable(BR.lessonHeader, data)
            }

            is Lesson -> {
                binding.setVariable(BR.model, data)
                binding.setVariable(BR.callback, callback)
            }
        }
    }
}

fun interface LessonCallback {
    fun onLessonTap(model: Lesson)
}