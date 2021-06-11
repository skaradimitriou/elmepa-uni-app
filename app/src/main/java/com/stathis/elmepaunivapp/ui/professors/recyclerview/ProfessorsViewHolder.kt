package com.stathis.elmepaunivapp.ui.professors.recyclerview

import android.view.View
import com.stathis.elmepaunivapp.R
import com.stathis.elmepaunivapp.abstraction.AbstractViewHolder
import com.stathis.elmepaunivapp.listeners.new.ElmepaClickListener
import com.stathis.elmepaunivapp.model.LocalModel
import com.stathis.elmepaunivapp.ui.professors.model.Professor
import kotlinx.android.synthetic.main.professor_item_row.view.*

class ProfessorsViewHolder(itemView : View, callback : ElmepaClickListener) : AbstractViewHolder(itemView,callback) {

    override fun present(data: LocalModel) {
        when(data){
            is Professor -> {
                itemView.professor_name.text = data.fullName
                itemView.professor_email.text = data.email

                when(data.gender){
                    "male" -> itemView.professor_img.setImageResource(R.drawable.male_professor)
                    "female" -> itemView.professor_img.setImageResource(R.drawable.female_professor)
                }
            }
        }
    }
}