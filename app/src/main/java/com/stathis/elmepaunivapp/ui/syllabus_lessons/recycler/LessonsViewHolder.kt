package com.stathis.elmepaunivapp.ui.syllabus_lessons.recycler

import android.view.View
import com.stathis.elmepaunivapp.R
import com.stathis.elmepaunivapp.abstraction.ElmepaViewHolder
import com.stathis.elmepaunivapp.callbacks.ElmepaClickListener
import com.stathis.elmepaunivapp.model.LocalModel
import com.stathis.elmepaunivapp.ui.syllabus_lessons.model.LessonHeader
import com.stathis.elmepaunivapp.ui.syllabus_lessons.model.Lesson
import kotlinx.android.synthetic.main.holder_lessons_header_item.view.*
import kotlinx.android.synthetic.main.lesson_item_row.view.*

class LessonsViewHolder(itemView : View, callback : ElmepaClickListener) : ElmepaViewHolder(itemView,callback) {

    override fun present(data: LocalModel) {
        when(data){
            is LessonHeader -> itemView.syllabus_lessons_header.text = data.title
            is Lesson -> {
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