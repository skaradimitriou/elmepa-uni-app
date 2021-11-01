package com.stathis.elmepaunivapp.ui.syllabus.recyclerview

import android.view.View
import com.stathis.elmepaunivapp.abstraction.ElmepaViewHolder
import com.stathis.elmepaunivapp.callbacks.ElmepaClickListener
import com.stathis.elmepaunivapp.abstraction.LocalModel
import com.stathis.elmepaunivapp.ui.syllabus.model.Semester
import kotlinx.android.synthetic.main.holder_semester_item_row.view.*

class SemesterViewHolder(itemView : View, callback : ElmepaClickListener) : ElmepaViewHolder(itemView,callback) {

    override fun present(data: LocalModel) {
        when(data){
            is Semester -> itemView.semester_item_txt.text = data.semester
        }
    }
}
