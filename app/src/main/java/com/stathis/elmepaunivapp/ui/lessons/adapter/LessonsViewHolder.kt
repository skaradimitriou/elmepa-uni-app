package com.stathis.elmepaunivapp.ui.lessons.adapter

import android.view.View
import androidx.databinding.ViewDataBinding
import com.stathis.elmepaunivapp.BR
import com.stathis.elmepaunivapp.abstraction.AbstractViewHolder
import com.stathis.elmepaunivapp.callbacks.ElmepaClickListener
import com.stathis.elmepaunivapp.abstraction.LocalModel
import com.stathis.elmepaunivapp.databinding.LessonItemRowBinding
import com.stathis.elmepaunivapp.ui.lessons.model.LessonHeader
import com.stathis.elmepaunivapp.ui.lessons.model.Lesson

class LessonsViewHolder(val binding : ViewDataBinding, val callback : ElmepaClickListener) : AbstractViewHolder(binding) {

    override fun present(data: LocalModel) {
        when(data){
            is LessonHeader -> binding.setVariable(BR.lessonHeader,data)
            is Lesson -> {
                binding.setVariable(BR.model,data)
                (binding as LessonItemRowBinding).lessonCard.setOnClickListener {
                    data.isExpanded = !data.isExpanded

                    when (data.isExpanded) {
                        true -> {
                            binding.iconMore.animate().rotation(90f).start()
                            binding.lessonDesc.visibility = View.VISIBLE
                        }
                        false -> {
                            binding.iconMore.animate().rotation(0f).start()
                            binding.lessonDesc.visibility = View.GONE
                        }
                    }
                }
            }
        }
    }
}