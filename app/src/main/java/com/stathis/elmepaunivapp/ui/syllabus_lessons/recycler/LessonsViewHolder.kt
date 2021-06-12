package com.stathis.elmepaunivapp.ui.syllabus_lessons.recycler

import android.view.View
import com.stathis.elmepaunivapp.R
import com.stathis.elmepaunivapp.abstraction.AbstractViewHolder
import com.stathis.elmepaunivapp.listeners.latest.ElmepaClickListener
import com.stathis.elmepaunivapp.model.LocalModel
import com.stathis.elmepaunivapp.ui.syllabus_lessons.model.LessonHeader
import com.stathis.elmepaunivapp.ui.syllabus_lessons.model.LessonKt
import kotlinx.android.synthetic.main.holder_lessons_header_item.view.*
import kotlinx.android.synthetic.main.lesson_item_row.view.*

class LessonsViewHolder(itemView : View, callback : ElmepaClickListener) : AbstractViewHolder(itemView,callback) {

    override fun present(data: LocalModel) {
        when(data){
            is LessonHeader -> itemView.syllabus_lessons_header.text = data.title
            is LessonKt -> {
                itemView.lesson_name.text = data.name
                itemView.mandatory_txt.text = data.mandatory
                itemView.lesson_description.text = data.description

                when(data.direction){
                    "data" -> itemView.lesson_name.setBackgroundColor(itemView.resources.getColor(R.color.lesson_green))
                    "mkt" -> itemView.lesson_name.setBackgroundColor(itemView.resources.getColor(R.color.lesson_blue))
                    "ba" -> itemView.lesson_name.setBackgroundColor(itemView.resources.getColor(R.color.dark_orange))
                }
            }
        }
    }
}