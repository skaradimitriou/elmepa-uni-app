package com.stathis.elmepaunivapp.ui.syllabus_lessons.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.stathis.elmepaunivapp.R
import com.stathis.elmepaunivapp.abstraction.DiffItemClass
import com.stathis.elmepaunivapp.callbacks.ElmepaClickListener
import com.stathis.elmepaunivapp.abstraction.LocalModel
import com.stathis.elmepaunivapp.ui.syllabus_lessons.model.LessonHeader
import com.stathis.elmepaunivapp.ui.syllabus_lessons.model.Lesson

class LessonsAdapter(private val callback: ElmepaClickListener) :
    ListAdapter<LocalModel, LessonsViewHolder>(DiffItemClass<LocalModel>()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LessonsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(viewType, parent, false)
        return LessonsViewHolder(view, callback)
    }

    override fun onBindViewHolder(holder: LessonsViewHolder, position: Int) {
        holder.bindData(getItem(position))
    }

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is LessonHeader -> R.layout.holder_lessons_header_item
            is Lesson -> R.layout.lesson_item_row
            else -> R.layout.holder_empty_view
        }
    }
}