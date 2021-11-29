package com.stathis.elmepaunivapp.ui.main.professors.recyclerview

import android.view.View
import com.stathis.elmepaunivapp.R
import com.stathis.elmepaunivapp.abstraction.ElmepaViewHolder
import com.stathis.elmepaunivapp.callbacks.ElmepaClickListener
import com.stathis.elmepaunivapp.abstraction.LocalModel
import com.stathis.elmepaunivapp.ui.main.professors.model.Professor
import kotlinx.android.synthetic.main.professor_item_row.view.*

class ProfessorsViewHolder(itemView : View, callback : ElmepaClickListener) : ElmepaViewHolder(itemView,callback) {

    override fun present(data: LocalModel) {
        when(data){
            is Professor -> {
                itemView.professor_name.text = data.fullName
                itemView.professor_email.text = data.email

                when(data.gender){
                    itemView.resources.getString(R.string.male) -> itemView.professor_img.setImageResource(R.drawable.male_professor)
                    itemView.resources.getString(R.string.female) -> itemView.professor_img.setImageResource(R.drawable.female_professor)
                }
            }
        }
    }
}