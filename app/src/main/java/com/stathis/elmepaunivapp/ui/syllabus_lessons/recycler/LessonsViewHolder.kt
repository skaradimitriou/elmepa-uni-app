package com.stathis.elmepaunivapp.ui.syllabus_lessons.recycler

import androidx.databinding.ViewDataBinding
import com.stathis.elmepaunivapp.BR
import com.stathis.elmepaunivapp.abstraction.AbstractViewHolder
import com.stathis.elmepaunivapp.callbacks.ElmepaClickListener
import com.stathis.elmepaunivapp.abstraction.LocalModel
import com.stathis.elmepaunivapp.ui.syllabus_lessons.model.LessonHeader
import com.stathis.elmepaunivapp.ui.syllabus_lessons.model.Lesson

class LessonsViewHolder(val binding : ViewDataBinding, val callback : ElmepaClickListener) : AbstractViewHolder(binding) {

    override fun present(data: LocalModel) {
        when(data){
            is LessonHeader -> binding.setVariable(BR.lessonHeader,data)
            is Lesson -> {
                binding.setVariable(BR.lesson,data)
                binding.setVariable(BR.callback,callback)
            }
        }
    }
}